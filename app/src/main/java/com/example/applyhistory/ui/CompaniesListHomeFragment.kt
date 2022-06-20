package com.example.applyhistory.ui

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.applyhistory.db.Company
import com.example.applyhistory.viewmodel.CompanyViewModel
import com.example.applyhistory.R
import com.example.applyhistory.databinding.FragmentCompaniesListHomeBinding
import com.example.applyhistory.util.Constants.ID
import com.example.applyhistory.util.Constants.INSERT_MODE
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CompaniesListHomeFragment : Fragment() {
    private lateinit var binding: FragmentCompaniesListHomeBinding
    private val companiesViewModel: CompanyViewModel by activityViewModels()


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentCompaniesListHomeBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.addCompany.setOnClickListener {
            val bundle = Bundle()
            bundle.putInt(INSERT_MODE,0)
            findNavController().navigate(R.id.action_companiesListHomeFragment_to_addCompanyFragment,bundle)
        }


        val adapter = CompaniesListAdapter()
        binding.recycler.adapter = adapter
        companiesViewModel.companiesList?.observe(viewLifecycleOwner){
            if (it != null){
                adapter.setList(it)
            }
        }

        binding.recycler.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                val visibility = if ((binding.recycler.layoutManager as LinearLayoutManager).findFirstCompletelyVisibleItemPosition() != 0) View.GONE else View.VISIBLE
                binding.addCompany.visibility = visibility
            }
        })

        adapter.setOnItemClickListener(object : CompaniesListAdapter.OnItemClickListener {
            override fun onItemClick(item: Company, position: Int) {
                val bundle = Bundle()
                bundle.putInt(ID,item.id)
                findNavController().navigate(R.id.action_companiesListHomeFragment_to_companyDetailsFragment,bundle)
            }
        })

        companiesViewModel.companiesCount.observe(viewLifecycleOwner){
            if (it != null){
                binding.count = it
            }
        }

        binding.companyName.addTextChangedListener (object : TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(query: CharSequence?, p1: Int, p2: Int, p3: Int) {
                adapter.filter.filter(query)
            }

            override fun afterTextChanged(query: Editable?) {
            }
        })
    }
}

