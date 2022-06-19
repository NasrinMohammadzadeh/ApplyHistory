package com.example.applyhistory.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.applyhistory.viewmodel.CompanyViewModel
import com.example.applyhistory.R
import com.example.applyhistory.databinding.FragmentCompanyDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
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

        binding.updateBtn.setOnClickListener {
            val bundle = Bundle()
            bundle.putInt("insert_mode",1)
            bundle.putInt("id",requireArguments().getInt("id"))
            findNavController().navigate(R.id.action_companyDetailsFragment_to_addCompanyFragment,bundle)
        }

        binding.deleteBtn.setOnClickListener {
            companiesViewModel.deleteCompany(requireArguments().getInt("id"))
            requireActivity().onBackPressed()
        }
    }

    override fun onStop() {
        super.onStop()
        companiesViewModel.resetCompany()
    }
}

