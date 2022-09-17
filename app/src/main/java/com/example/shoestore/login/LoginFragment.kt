package com.example.shoestore.login

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.shoestore.R
import com.example.shoestore.databinding.FragmentLoginBinding
import com.google.android.material.snackbar.Snackbar

/**
 * A fragment for logging in and registering an account.
 */
class LoginFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment.
        val binding: FragmentLoginBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_login, container, false
        )
        val viewModel = ViewModelProvider(this)[LoginViewModel::class.java]
        // Read the data from the text views.
        val account = Account(
            emailAddress = binding.editTextLoginEmail.text.toString(),
            password = binding.editTextLoginPassword.text.toString()
        )
        binding.buttonLogin.setOnClickListener {
            // Verify login, the navigate back to the welcome fragment.
            if (viewModel.verifyLogin(account))
                findNavController().navigate(R.id.action_welcomeFragment_to_loginFragment)
            else
                // Show a snackbar with an error message.
                showSnackbar("Invalid username or password")
        }
        binding.buttonRegister.setOnClickListener {
            try {
                // Try to register, then if registration hasn't
                // failed, navigate back to the welcome fragment.
                viewModel.register(account)
                findNavController().navigate(R.id.action_welcomeFragment_to_loginFragment)
            } catch (e: RegistrationError) {
                // Show a snackbar with the error's message.
                showSnackbar(e.message)
            }
        }
        return binding.root
    }

    /**
     * Shows [message] in a snackbar for [duration] milliseconds.
     */
    private fun showSnackbar(message: String, duration: Int = Snackbar.LENGTH_LONG) {
        Snackbar.make(requireView(), message, duration).show()
    }
}