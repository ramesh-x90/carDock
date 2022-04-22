package com.example.carDock.domain.use_case


import android.database.sqlite.SQLiteConstraintException
import com.example.carDock.AppModule
import com.example.carDock.domain.model.BaseUser
import com.example.carDock.domain.model.Car
import com.example.carDock.domain.model.User
import com.example.carDock.domain.repository.UserRepository
import com.example.carDock.domain.util.*
import com.example.carDock.domain.util.utilModels.UserFlowData
import com.example.carDock.globalState.CurrentUserState
import kotlinx.coroutines.flow.Flow

object UserUseCases {

    private val userRepository: UserRepository = AppModule.getDSRepoServiceLocator().getUserRepositoryImpl()



    suspend fun login(email: String, password: String): UserAuthResult {
        if (Validators.validateEmail(email).and(password.isNotBlank())) {
            val authUser = authenticate(email, password)


            if (authUser != null) {
                CurrentUserState(authUser)
                return UserAuthResult.Success(authUser)
            }
        }

        return UserAuthResult.Failed

    }

    private suspend fun authenticate(email: String, password: String): BaseUser? =
        userRepository.getAuthUser(email = email, psw = password)


    suspend fun getUserById(id: Long): BaseUser? = userRepository.getUserById(id)


    suspend fun getUserBalance(id: Long): Long? = userRepository.getUserBalance(id)

    suspend fun registerUser(user: User): UserRegResult {
        val res = user.validateUser()

        if (res is UserRegResult.Failed) return res
        try {
            userRepository.addUser(user)
        } catch (e: SQLiteConstraintException) {
            return UserRegResult.Failed.DbError("email is already exists")
        } catch (e: Exception) {
            return UserRegResult.Failed.DbError("Data base Exception")
        }

        return UserRegResult.Success
    }

    suspend fun buyACar(id : Long): CarBuyResults {
        val buyer = CurrentUserState.getCurrentUser() ?:
        return CarBuyResults.Failed.InvalidUserCredential


        val car = CarUseCases.getCarById(id) ?:
        return CarBuyResults.Failed.InvalidCar

        if(!validatePurchase(buyer.id , car)) return CarBuyResults.Failed.NotEnoughBalance
        if(!CarUseCases.isCarAvailable(id)) {
            return CarBuyResults.Failed.CarIsAlreadySold
        }


        try {
            CarUseCases.buyACar(id)
            userRepository.deductBalance(buyer.id , car.price)

            val profit = car.price*70/100

            userRepository.deposit(car.seller , profit)

            //TODO add this car and user to sold car list

        }catch (e : NotEnoughBalanceException){
            //error
            return CarBuyResults.Failed.NotEnoughBalance
        }catch (e : InvalidCarException) {
            return CarBuyResults.Failed.InvalidCar
        }catch (e : SoldCarException){

            return CarBuyResults.Failed.CarIsAlreadySold
        }


        return CarBuyResults.Success



    }


    private suspend fun validatePurchase(id : Long, car : Car): Boolean {
        val bal = getUserBalance(id)!!
        return bal >= car.price
    }



    fun getAllUsers() = userRepository.getAllUsers()


    fun getUserDataFlow() : Flow<UserFlowData>?
    {
        val id = CurrentUserState.getCurrentUser()?.id
        return id?.let { userRepository.getUserDataFlow(it) }
    }
}