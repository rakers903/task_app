package com.example.tasks.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.navigation.fragment.NavHostFragment.Companion.findNavController
import androidx.navigation.fragment.findNavController
import com.example.tasks.R
import com.example.tasks.databinding.FragmentLoginBinding
import com.example.tasks.utils.UiState

class LoginFragment : BaseFragment() {

    private val binding by lazy {
        FragmentLoginBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding.loginButton.setOnClickListener {
            viewModel.authenticate(requireContext())
        }
        viewModel.loginState.observe(viewLifecycleOwner){
            when(it){
                is UiState.ERROR -> {
                    Toast.makeText(requireContext(),"Login failed",Toast.LENGTH_LONG).show()
                }
                UiState.LOADING -> {}
                is UiState.SUCCESS -> {
                    Toast.makeText(requireContext(),"Login succeed",Toast.LENGTH_LONG).show()
                    findNavController().navigate(R.id.action_login_to_tasks)
                }
            }
        }
        return binding.root
    }

}