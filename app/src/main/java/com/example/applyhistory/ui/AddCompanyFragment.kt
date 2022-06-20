package com.example.applyhistory.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.applyhistory.db.Company
import com.example.applyhistory.viewmodel.CompanyViewModel
import com.example.applyhistory.util.DateAndTimeUtil
import com.example.applyhistory.R
import com.example.applyhistory.databinding.FragmentAddCompanyBinding
import com.example.applyhistory.util.Constants.ID
import com.example.applyhistory.util.Constants.INSERT_MODE
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddCompanyFragment : Fragment() {
    private lateinit var binding: FragmentAddCompanyBinding
    private val companiesViewModel: CompanyViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddCompanyBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.mode = requireArguments().getInt(INSERT_MODE,0)

        val adapter = ArrayAdapter.createFromResource(
            requireActivity(),
            R.array.apply_status, android.R.layout.simple_spinner_item
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.applyStatus.adapter = adapter


        if (requireArguments().containsKey(ID)){
            companiesViewModel.getCompany(requireArguments().getInt(ID))
            companiesViewModel.company.observe(viewLifecycleOwner){
                it?.let {
                    binding.item = it
                }
            }
        }

        binding.addBtn.setOnClickListener {
            if (requireArguments().getInt(INSERT_MODE) == 0){
                addCompany()
            }else if (requireArguments().getInt(INSERT_MODE) == 1){
                updateCompany()
            }
            requireActivity().onBackPressed()
        }
    }

    private fun addCompany(){
        companiesViewModel.addCompany(
            Company(
                id = 0,
                companyName = binding.companyName.text.toString(),
                companyWebSite = binding.companyWeb.text.toString(),
                description = binding.description.text.toString(),
                lastUpdateDate = DateAndTimeUtil.getCurrentPersianDate()!!,
                applyStatus = binding.applyStatus.selectedItemPosition
            )
        )
    }

    private fun updateCompany(){
        companiesViewModel.updateCompany(
            Company(
                id = requireArguments().getInt(ID),
                companyName = binding.companyName.text.toString(),
                companyWebSite = binding.companyWeb.text.toString(),
                description = binding.description.text.toString(),
                lastUpdateDate = DateAndTimeUtil.getCurrentPersianDate()!!,
                applyStatus = binding.applyStatus.selectedItemPosition
            )
        )
    }

}

