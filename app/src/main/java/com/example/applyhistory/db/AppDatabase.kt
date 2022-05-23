package com.example.applyhistory.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Company::class], version = 1)
abstract class AppDatabase : RoomDatabase(){

    abstract fun companyDao(): CompanyDao

    companion object{
        const val DATABASE_NAME: String = "myDB"
    }

}