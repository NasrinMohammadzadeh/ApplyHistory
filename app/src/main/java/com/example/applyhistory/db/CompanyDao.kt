package com.example.applyhistory.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.applyhistory.db.Company

@Dao
interface CompanyDao {
    @Query("SELECT * FROM Company ORDER BY id DESC")
    fun getAll(): LiveData<List<Company>>

    @Query("SELECT * FROM Company WHERE id = :id")
    fun getQuestion(id : Int): Company?

    @Query("SELECT COUNT(id) FROM Company")
    fun getQuestionCount(): LiveData<Int>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg questions: Company)

    @Query("UPDATE Company SET description = :description, companyName= :companyName, companyWebSite= :companyWeb, lastUpdateDate= :lastUpdateDate, applyStatus= :applyStatus WHERE id =:id")
    fun update(description: String?, companyName: String?,companyWeb: String?,lastUpdateDate: String?, applyStatus: Int?, id: Int)

}