package com.sanjeet.simpletrancsaction.view.Home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.coroutineScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.sanjeet.simpletrancsaction.databinding.FragmentHomeBinding
import com.sanjeet.simpletrancsaction.view.Home.adapter.HomeAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private val homeAdapter = HomeAdapter()
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding

    private val viewModel: HomeViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        binding?.progressBar?.visibility = View.VISIBLE
        binding?.rvAccounts?.apply {
            adapter = homeAdapter
            layoutManager = LinearLayoutManager(requireActivity())
        }
        return binding?.root!!
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getAccounts()

        lifecycle.coroutineScope.launchWhenCreated {
            viewModel.accounts.collect {
                if (it.isLoading) {
                    binding?.progressBar?.visibility = View.VISIBLE
                }
                if (it.error.isNotBlank()) {
                    binding?.progressBar?.visibility = View.GONE
                    Toast.makeText(requireContext(), it.error, Toast.LENGTH_SHORT).show()
                }
                it.data?.let {
                    binding?.progressBar?.visibility = View.GONE
                    homeAdapter.setContentList(it.toMutableList())
                }
            }
        }

        homeAdapter.itemClickListener {
            val action = HomeFragmentDirections.actionHomeFragmentToTransactionFragment(it.id)
            findNavController().navigate(action)
        }

    }

}