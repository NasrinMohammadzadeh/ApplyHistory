package com.example.applyhistory.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.applyhistory.db.AppDatabase
import com.example.applyhistory.db.Company
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import javax.inject.Inject

class CompanyRepository @Inject constructor(private val db: AppDatabase) {
    var companies : LiveData<List<Company>> = MutableLiveData()
    var companiesCount : LiveData<Int> = MutableLiveData()


    @JvmName("getCompanies1")
    fun getCompanies(){
        companies = db.companyDao().getAll()
    }

    fun getCompaniesCount()  {
        companiesCount = db.companyDao().getCompaniesCount()
    }

    fun getCompany(num : Int):Company? {
        return db.companyDao().getCompany(num)
    }

    fun addCompany(company : Company) {
        CoroutineScope(IO).launch{
            db.companyDao().insert(
                company
            )
        }

    }

    fun updateCompany(company : Company) {
        CoroutineScope(IO).launch{
            db.companyDao().update(
                id = company.id,
                description = company.description,
                companyName = company.companyName,
                companyWeb = company.companyWebSite,
                lastUpdateDate = company.lastUpdateDate,
                applyStatus = company.applyStatus
            )
        }

    }

    fun deleteCompany(id: Int) {
        CoroutineScope(IO).launch{
            db.companyDao().deleteCompany(
                id = id
            )
        }

    }

}