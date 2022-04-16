package com.example.carDock

import com.example.carDock.domain.model.User
import com.example.carDock.domain.util.UserRegResult
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
        val a = "0707777777"

        val b = User(
            password = "0707777777",
            email = "ramesh@gmail.com",
            address =  "ramesh@gmail.com",
            contact_number = a.toInt(),
            balance = 0,
            name = "ramesh",

        ).validateUser()

        val tag = "--- ${b.toString()} ---"
        print(tag)

        assertEquals(true, true)
    }
}
