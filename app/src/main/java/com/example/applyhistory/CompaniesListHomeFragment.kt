package com.example.applyhistory

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.applyhistory.databinding.FragmentCompaniesListHomeBinding

class CompaniesListHomeFragment : Fragment() {
    private lateinit var binding: FragmentCompaniesListHomeBinding
    val companiesViewModel: CompanyViewModel by activityViewModels()


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentCompaniesListHomeBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.addCompany.setOnClickListener {
            findNavController().navigate(R.id.action_companiesListHomeFragment_to_addCompanyFragment)
        }
    }
}

