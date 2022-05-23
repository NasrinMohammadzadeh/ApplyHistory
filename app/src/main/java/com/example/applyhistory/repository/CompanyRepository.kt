package com.example.applyhistory.repository

import android.content.Context
import androidx.lifecycle.LiveData
import com.example.applyhistory.db.AppDatabase
import com.example.applyhistory.db.Company
import com.example.applyhistory.db.CompanyDao

class CompanyRepository {
    var db : AppDatabase? = null
    var companyDao  : CompanyDao? = null

    fun initDB(context : Context){
        db = AppDatabase.getAppDataBase(context)

        companyDao = db?.companyDao()

    }

    fun getCompanies() : LiveData<List<Company>>{
        return db!!.companyDao().getAll()
    }

    fun getCompaniesCount() = db!!.companyDao().getQuestionCount()

    fun getCompany(num : Int) = db!!.companyDao().getQuestion(num)

    fun addCompany(company : Company) {
        db!!.companyDao().insert(
            company
        )
    }

    fun updateCompany(company : Company) {
        db!!.companyDao().update(
            id = company.id,
            description = company.description,
            companyName = company.companyName,
            companyWeb = company.companyWebSite,
            lastUpdateDate = company.lastUpdateDate,
            applyStatus = company.applyStatus
        )
    }

}