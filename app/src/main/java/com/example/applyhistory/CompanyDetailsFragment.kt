package com.example.applyhistory

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.applyhistory.databinding.FragmentCompanyDetailBinding

class CompanyDetailsFragment : Fragment() {
    private lateinit var binding: FragmentCompanyDetailBinding
    private val companiesViewModel: CompanyViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCompanyDetailBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        companiesViewModel.getCompany(requireArguments().getInt("id"))
        companiesViewModel.company.observe(viewLifecycleOwner){
            it?.let {
                binding.item = it
            }
        }
    }
}

