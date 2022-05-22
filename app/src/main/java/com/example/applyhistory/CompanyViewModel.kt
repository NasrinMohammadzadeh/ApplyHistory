package com.example.applyhistory

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class CompanyViewModel(app: Application): AndroidViewModel(app) {

    var companiesCount    : LiveData<Int>
    var company  =  MutableLiveData<Company?>()
    var companyRepository = CompanyRepository()
    var companiesList: LiveData<List<Company>>? = null

    init{
        companyRepository.initDB(app.applicationContext)
        companiesCount = companyRepository.getCompaniesCount()
        companiesList = companyRepository.getCompanies()
    }

    fun addCompany(company: Company){
        companyRepository.addCompany(company)
    }

    fun getCompany(id: Int){
        company.postValue( companyRepository.getCompany(id))
    }

}

