package com.example.applyhistory

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Company::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun companyDao(): CompanyDao

    companion object {
        var INSTANCE: AppDatabase? = null

        fun getAppDataBase(context: Context): AppDatabase {
            val db : AppDatabase by lazy {
                Room.databaseBuilder(context.applicationContext,
                    AppDatabase::class.java, "myDB")
                    .allowMainThreadQueries()
                    .build()
            }
            return db
        }

        fun destroyDataBase(){
            INSTANCE = null
        }
    }

}