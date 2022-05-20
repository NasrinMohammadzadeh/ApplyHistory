package com.example.applyhistory

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface CompanyDao {
    @Query("SELECT * FROM Company")
    fun getAll(): LiveData<List<Company>>

    @Query("SELECT * FROM Company WHERE id = :id")
    fun getQuestion(id : Int): Company?

    @Query("SELECT COUNT(id) FROM Company")
    fun getQuestionCount(): LiveData<Int>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg questions: Company)

}