package com.example.carDock.domain.repository



import com.example.carDock.domain.model.BaseUser
import com.example.carDock.domain.model.User
import kotlinx.coroutines.flow.Flow

interface UserRepository {

    fun getAllUsers(): Flow<List<BaseUser>>?
    suspend fun getUserById(id: Long): BaseUser?
    suspend fun addUser(user: User)
    suspend fun delUser(id: Long)
    suspend fun getUserBalance(id: Long): Long?
    suspend fun getAuthUser(email: String, psw: String): BaseUser?
}