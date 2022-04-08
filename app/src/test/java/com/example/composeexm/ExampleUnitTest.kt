package com.example.composeexm

import com.example.composeexm.domain.model.User
import com.example.composeexm.domain.util.UserRegResult
import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun userValidationText() {
        val user = User(
            name = "ramesh",
            email = "ramesh@gmail.com",
            password = "we41234fdaf",
            contact_number = 1111111111,
            address = "eqrecdaff",
            balance = 123,
        )
        val res = user.validateUser()
        val a = when (res) {
            UserRegResult.Failed.DbError -> false
            UserRegResult.Failed.InvalidAddress -> false
            UserRegResult.Failed.InvalidContactNumber -> false
            UserRegResult.Failed.InvalidEmail -> false
            UserRegResult.Failed.InvalidInitBalance -> false
            UserRegResult.Failed.InvalidPassword -> false
            UserRegResult.Failed.InvalidUserName -> false
            UserRegResult.Success -> true
        }
        assertEquals(true, a)
    }
}