package com.example.applyhistory

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.applyhistory.databinding.FragmentAddCompanyBinding

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

        binding.mode = requireArguments().getInt("insert_mode",0)

        val adapter = ArrayAdapter.createFromResource(
            requireActivity(),
            R.array.apply_status, android.R.layout.simple_spinner_item
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.applyStatus.adapter = adapter


        if (requireArguments().containsKey("id")){
            companiesViewModel.getCompany(requireArguments().getInt("id"))
            companiesViewModel.company.observe(viewLifecycleOwner){
                it?.let {
                    binding.item = it
                }
            }
        }

        binding.addBtn.setOnClickListener {
            if (requireArguments().getInt("insert_mode") == 0){
                addCompany()
            }else if (requireArguments().getInt("insert_mode") == 1){
                updateCompany()
            }
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
                id = requireArguments().getInt("id"),
                companyName = binding.companyName.text.toString(),
                companyWebSite = binding.companyWeb.text.toString(),
                description = binding.description.text.toString(),
                lastUpdateDate = DateAndTimeUtil.getCurrentPersianDate()!!,
                applyStatus = binding.applyStatus.selectedItemPosition
            )
        )
    }

}

