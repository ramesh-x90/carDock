package com.example.carDock.domain.use_case


import android.database.sqlite.SQLiteConstraintException
import com.example.carDock.AppModule
import com.example.carDock.domain.model.BaseUser
import com.example.carDock.domain.model.User
import com.example.carDock.domain.repository.UserRepository
import com.example.carDock.domain.util.UserAuthResult
import com.example.carDock.domain.util.UserRegResult
import com.example.carDock.domain.util.Validators
import com.example.carDock.globalState.CurrentUserState

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



    fun getAllUsers() = userRepository.getAllUsers()
}