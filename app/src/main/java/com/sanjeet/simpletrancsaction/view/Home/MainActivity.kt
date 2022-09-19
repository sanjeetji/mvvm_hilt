package com.sanjeet.simpletrancsaction.view.Home

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.sanjeet.simpletrancsaction.R
import com.sanjeet.simpletrancsaction.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        (supportFragmentManager.findFragmentById(R.id.navHostFragment) as NavHostFragment).navController.apply {
            binding.bnvAppBottomNav.setupWithNavController(this)
            addOnDestinationChangedListener { _, destination, _ ->
                binding.bnvAppBottomNav.visibility = when (destination.id) {
                    R.id.homeFragment,
                    R.id.shopingFragment,
                    R.id.notificationFragment,
                    R.id.profileFragment,
                    R.id.transactionFragment
                    -> View.VISIBLE
                    else -> View.GONE
                }
            }
        }
    }
}