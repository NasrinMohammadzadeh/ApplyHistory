package com.example.applyhistory

import android.content.Context
import androidx.lifecycle.LiveData

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

}