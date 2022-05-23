package com.example.applyhistory.db

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class Company(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val companyName: String,
    val companyWebSite: String,
    val lastUpdateDate: String,
    val description: String,
    val applyStatus: Int?
)