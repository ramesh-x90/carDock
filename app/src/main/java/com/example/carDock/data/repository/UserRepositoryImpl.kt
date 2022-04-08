package com.example.carDock.data.repository

import com.example.carDock.data.data_source.UserDao
import com.example.carDock.domain.model.BaseUser
import com.example.carDock.domain.model.User
import com.example.carDock.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow

class UserRepositoryImpl(private val dao: UserDao) : UserRepository {
    override fun getAllUsers(): Flow<List<BaseUser>>? = dao.getUsers()

    override fun getUserById(id: Long): BaseUser? = dao.getUserById(id)

    override suspend fun addUser(user: User) = dao.addUser(user)

    override suspend fun delUser(id: Long) = dao.delUser(id)

    override fun getUserBalance(id: Long): Long? = dao.getUserBalance(id)

    override fun getAuthUser(email: String, psw: String): BaseUser? = dao.getAuthUser(email, psw)
}