package com.example.carDock.data.data_source

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.carDock.domain.model.BaseUser
import com.example.carDock.domain.model.User
import com.example.carDock.domain.util.utilModels.UserFlowData
import kotlinx.coroutines.flow.Flow


@Dao
interface UserDao {

    @Query(
        "SELECT id , name , email , contact_number , address FROM user"
    )
    fun getUsers(): Flow<List<BaseUser>>?

    @Query(
        "SELECT id , name , email , contact_number , address FROM user WHERE id = :id"
    )
    suspend fun getUserById(id: Long): BaseUser?

    @Insert(onConflict = OnConflictStrategy.ABORT, entity = User::class)
    suspend fun addUser(user: User)

    @Query(
        "DELETE FROM user WHERE id = :id"
    )
    suspend fun delUser(id: Long)

    @Query(
        "SELECT balance FROM user WHERE id = :id"
    )
    suspend fun getUserBalance(id: Long): Long?

    @Query(
        "SELECT id , name , email , contact_number , address FROM user WHERE email = :email AND password = :psw"
    )
    suspend fun getAuthUser(email: String, psw: String): BaseUser?

    @Query(
        "UPDATE user SET balance = :bal WHERE id = :id"
    )
    suspend fun changeBalance(id : Long , bal : Long)


    @Query(
        "SELECT id , name , email , contact_number , address , balance FROM user WHERE id = :id"
    )
    fun getUserDataFlow(id : Long) : Flow<UserFlowData>?


}