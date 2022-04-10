package com.example.carDock.data.repository

import com.example.carDock.data.data_source.UserDao
import com.example.carDock.domain.model.BaseUser
import com.example.carDock.domain.model.User
import com.example.carDock.domain.repository.UserRepository
import com.example.carDock.domain.util.NotEnoughBalanceException
import com.example.carDock.domain.util.utilModels.UserFlowData
import kotlinx.coroutines.flow.Flow

class UserRepositoryImpl(private val dao: UserDao) : UserRepository {
    override fun getAllUsers(): Flow<List<BaseUser>>? = dao.getUsers()

    override suspend fun getUserById(id: Long): BaseUser? = dao.getUserById(id)

    override suspend fun addUser(user: User) = dao.addUser(user)

    override suspend fun delUser(id: Long) = dao.delUser(id)

    override suspend fun getUserBalance(id: Long): Long? = dao.getUserBalance(id)

    override suspend fun getAuthUser(email: String, psw: String): BaseUser? = dao.getAuthUser(email, psw)

    override suspend fun deductBalance(id: Long, amount: Long) {
        val userBal = getUserBalance(id)

        if (userBal != null) {
            if(userBal >= amount){
                val newBal = userBal - amount
                dao.changeBalance(id , newBal)
            }else{
                throw NotEnoughBalanceException("Not Enough Balance in user account")
            }
        }
    }


    override fun getUserDataFlow(id: Long): Flow<UserFlowData>? = dao.getUserDataFlow(id)
}