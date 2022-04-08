package com.example.carDock.domain.model

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.example.carDock.domain.util.UserRegResult
import com.example.carDock.domain.util.Validators
import com.example.carDock.serviceLocators.CustomerServiceLocator


@Entity(tableName = "user", indices = [Index(value = ["email"], unique = true)])
data class User(
    @PrimaryKey(autoGenerate = true) val id: Long? = null,

    val name: String,
    val email: String,
    val contact_number: Int,
    val address: String,

    val balance: Long = CustomerServiceLocator.getInitBalance(),

    //for simplicity password is stored in here
    val password: String,
) {

    fun validateUser(): UserRegResult {
        return when {
            name.isBlank() -> UserRegResult.Failed.InvalidUserName
            email.isBlank()
                .or(!Validators.validateEmail(email)) -> UserRegResult.Failed.InvalidEmail
            address.isBlank() -> UserRegResult.Failed.InvalidAddress
            !Validators.validateContactNum(contact_number.toString()) -> UserRegResult.Failed.InvalidContactNumber
            password.isBlank() -> UserRegResult.Failed.InvalidPassword
            balance < 0 -> UserRegResult.Failed.InvalidInitBalance
            else -> UserRegResult.Success

        }
    }


}