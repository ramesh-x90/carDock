package com.example.carDock.domain.repository



import com.example.carDock.domain.util.NotEnoughBalanceException
import com.example.carDock.domain.model.BaseUser
import com.example.carDock.domain.model.User
import com.example.carDock.domain.util.utilModels.UserFlowData
import kotlinx.coroutines.flow.Flow
import kotlin.jvm.Throws

interface UserRepository {

    fun getAllUsers(): Flow<List<BaseUser>>?
    suspend fun getUserById(id: Long): BaseUser?
    suspend fun addUser(user: User)
    suspend fun delUser(id: Long)
    suspend fun getUserBalance(id: Long): Long?
    suspend fun getAuthUser(email: String, psw: String): BaseUser?

    @Throws(exceptionClasses = [NotEnoughBalanceException :: class])
    suspend fun deductBalance(id : Long , amount : Long)

    suspend fun deposit(id : Long, amount : Long)

    fun getUserDataFlow(id : Long) : Flow<UserFlowData>?
}