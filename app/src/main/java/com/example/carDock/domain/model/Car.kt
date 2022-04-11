package com.example.carDock.domain.model


import androidx.compose.ui.graphics.Color
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "car")
data class Car(
    @PrimaryKey(autoGenerate = true) val id: Long? = null,

    val model: String,
    val Color: Int,
    val brand: String,
    val chassis_no: String,
    val engine_no: String,
    val fuelType: String,
    val timestamp: Long = System.currentTimeMillis(),


    val launchedYear : String,

    val price: Long,

    val seller: Long,
    val availability: Boolean = true


) {
    companion object {
        val colors: List<Color> = listOf(
            Color.Red,
            Color.Yellow,
            Color.Black,
            Color.Blue,
            Color.Gray,
            Color.Green,
            Color.White,
            Color.Cyan,
            Color.Magenta
        )
    }


}