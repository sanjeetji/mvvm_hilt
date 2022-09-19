package com.sanjeet.simpletrancsaction.view.login

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.coroutineScope
import com.sanjeet.simpletrancsaction.databinding.ActivityUserLoginBinding
import com.sanjeet.simpletrancsaction.view.Home.MainActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UserLoginActivity : AppCompatActivity() {

    lateinit var binding: ActivityUserLoginBinding
    private val userLoginViewModel: UserLoginViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserLoginBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.buttonLogin.setOnClickListener {
            if (binding.etUsername.text.isNullOrEmpty()) {
                binding.etUsername.error = "Enter username"
                binding.etUsername.requestFocus()
                return@setOnClickListener
            } else if (binding.etPassword.text.isNullOrEmpty()) {
                binding.etPassword.error = "Enter password"
                binding.etPassword.requestFocus()
                return@setOnClickListener
            } else {
                userLoginViewModel.userLogin(
                    binding.etUsername.text.toString(),
                    binding.etPassword.text.toString()
                )
            }
        }
        lifecycle.coroutineScope.launchWhenCreated {
            userLoginViewModel.userLogin.collect {
                if (it.isLoading) {
                    binding.progressBar.visibility = View.VISIBLE
                }
                if (it.error.isNotBlank()) {
                    binding.progressBar.visibility = View.GONE
                    Toast.makeText(this@UserLoginActivity, it.error, Toast.LENGTH_SHORT).show()
                }
                it.data?.let {
                    binding.progressBar.visibility = View.GONE
                    Toast.makeText(this@UserLoginActivity, "Login Success", Toast.LENGTH_SHORT)
                        .show()
                    startActivity(Intent(this@UserLoginActivity, MainActivity::class.java))
                }
            }
        }
    }
}