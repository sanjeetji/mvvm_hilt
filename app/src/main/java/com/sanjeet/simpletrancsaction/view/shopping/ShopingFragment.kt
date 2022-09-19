package com.sanjeet.simpletrancsaction.view.shopping

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.sanjeet.simpletrancsaction.databinding.FragmentShopingBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ShopingFragment : Fragment() {

    private var _binding: FragmentShopingBinding? = null
    private val binding get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentShopingBinding.inflate(inflater, container, false)
        return binding?.root
    }

}