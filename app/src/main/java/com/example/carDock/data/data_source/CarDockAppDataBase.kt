package com.example.carDock.data.data_source

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.carDock.domain.model.Car
import com.example.carDock.domain.model.User


@Database(
    entities = [Car::class, User::class],
    version = 1
)
abstract class CarDockAppDataBase : RoomDatabase() {
    abstract fun carDao(): CarDao
    abstract fun userDao(): UserDao

    companion object {
        @Volatile
        private var instance: CarDockAppDataBase? = null
        private val LOCK = Any()

        fun getInstance(context: Context): CarDockAppDataBase {
            return (
                    instance ?: synchronized(LOCK) {
                        instance ?: buildDatabase(context).also { instance = it }
                    }
                    )
        }

        private fun buildDatabase(context: Context): CarDockAppDataBase {
            return (
                    Room.databaseBuilder(
                        context,
                        CarDockAppDataBase::class.java, "car_dock_db"
                    )
//                        .allowMainThreadQueries()
                        .build()
                    )

        }
    }
}