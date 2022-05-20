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
    val companiesViewModel: CompanyViewModel by activityViewModels()


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

        val adapter = ArrayAdapter.createFromResource(
            requireActivity(),
            R.array.apply_status, android.R.layout.simple_spinner_item
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.applyStatus.adapter = adapter


        binding.addBtn.setOnClickListener {
            companiesViewModel.addCompany(
                Company(
                    id = 0,
                    companyName = binding.companyName.text.toString(),
                    companyWebSite = binding.companyWeb.text.toString(),
                    description = binding.description.text.toString(),
                    lastUpdateDate = DateAndTimeUtil.getCurrentPersianTime()!!,
                    applyStatus = binding.applyStatus.selectedItemPosition
                )
            )
        }
    }
}

