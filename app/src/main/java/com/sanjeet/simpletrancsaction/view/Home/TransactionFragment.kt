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
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.sanjeet.simpletrancsaction.databinding.FragmentTransactionBinding
import com.sanjeet.simpletrancsaction.view.Home.adapter.TransactionAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TransactionFragment : Fragment() {
    private val transactionAdapter = TransactionAdapter()
    val args: TransactionFragmentArgs by navArgs()
    private var _binding: FragmentTransactionBinding? = null
    private val binding get() = _binding

    private val viewModel: HomeViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentTransactionBinding.inflate(inflater, container, false)

        binding?.progressBar?.visibility = View.VISIBLE
        binding?.rvTransactions?.apply {
            adapter = transactionAdapter
            layoutManager = LinearLayoutManager(requireActivity())
        }
        binding?.backImg?.setOnClickListener {
            findNavController().popBackStack()
        }
        return binding?.root!!
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        args.let {
            viewModel.getTransactions(it.id!!)
        }

        lifecycle.coroutineScope.launchWhenCreated {
            viewModel.transaction.collect {
                if (it.isLoading) {
                    binding?.progressBar?.visibility = View.VISIBLE
                }
                if (it.error.isNotBlank()) {
                    binding?.progressBar?.visibility = View.GONE
                    Toast.makeText(requireContext(), it.error, Toast.LENGTH_SHORT).show()
                }
                it.data?.let {
                    binding?.progressBar?.visibility = View.GONE
                    transactionAdapter.setContentList(it.toMutableList())
                }
            }
        }

    }

}