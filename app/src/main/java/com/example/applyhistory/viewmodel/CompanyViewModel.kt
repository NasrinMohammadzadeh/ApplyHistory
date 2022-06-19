package com.example.applyhistory.viewmodel

import androidx.lifecycle.*
import com.example.applyhistory.db.Company
import com.example.applyhistory.repository.CompanyRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CompanyViewModel @Inject constructor(private val companyRepository : CompanyRepository): ViewModel() {

    var companiesCount : LiveData<Int>
    var company  =  MutableLiveData<Company?>()
    var companiesList: LiveData<List<Company>>? = null

    init{
        companyRepository.getCompaniesCount()
        companiesCount = companyRepository.companiesCount
        companyRepository.getCompanies()
        companiesList = companyRepository.companies
    }

    fun addCompany(company: Company){
        companyRepository.addCompany(company)
    }


    fun updateCompany(company: Company){
        companyRepository.updateCompany(company)
    }

    fun deleteCompany(id: Int){
        companyRepository.deleteCompany(id)
    }

    fun getCompany(id: Int){
        CoroutineScope(IO).launch {
            company.postValue( companyRepository.getCompany(id))
        }
    }

    fun resetCompany(){
        company.value = null
    }

}

