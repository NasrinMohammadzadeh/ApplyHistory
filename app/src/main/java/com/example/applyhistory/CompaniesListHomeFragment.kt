package com.example.applyhistory

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
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
            val bundle = Bundle()
            bundle.putInt("insert_mode",0)
            findNavController().navigate(R.id.action_companiesListHomeFragment_to_addCompanyFragment,bundle)
        }


        val adapter = CompaniesListAdapter()
        binding.recycler.adapter = adapter
        companiesViewModel.companiesList?.observe(viewLifecycleOwner){
            if (it != null){
                adapter.submitList(it)
            }
        }

        binding.recycler.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                val visibility = if ((binding.recycler.layoutManager as LinearLayoutManager).findFirstCompletelyVisibleItemPosition() != 0) View.GONE else View.VISIBLE
                binding.addCompany.visibility = visibility
            }
        })

        adapter.setOnItemClickListener(object : CompaniesListAdapter.OnItemClickListener{
            override fun onItemClick(item: Company, position: Int) {
                val bundle = Bundle()
                bundle.putInt("id",item.id)
                findNavController().navigate(R.id.action_companiesListHomeFragment_to_companyDetailsFragment,bundle)
            }
        })

        companiesViewModel.companiesCount.observe(viewLifecycleOwner){
            if (it != null){
                binding.count = it
            }
        }
    }
}

