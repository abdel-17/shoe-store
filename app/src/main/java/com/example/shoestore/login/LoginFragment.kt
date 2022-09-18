package com.example.shoestore.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.shoestore.R
import com.example.shoestore.databinding.FragmentLoginBinding
import com.google.android.material.snackbar.Snackbar

class LoginFragment : Fragment() {
    private val viewModel: LoginViewModel by viewModels()

    private lateinit var binding: FragmentLoginBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_login, container, false
        )
        // Setup the actions of the buttons.
        binding.buttonLogin.setOnClickListener { login() }
        binding.buttonRegister.setOnClickListener { register() }
        return binding.root
    }

    // The account data currently in the input fields.
    private val account
        get() = Account(
            email = binding.editTextLoginEmail.text.toString(),
            password = binding.editTextLoginPassword.text.toString()
        )

    /**
     * Logs into the account and navigates to the
     * welcome fragment if login was successful;
     * otherwise, shows an error message.
     */
    private fun login() {
        val account = this.account
        if (viewModel.isRegistered(account))
            navigateToWelcomeFragment(account)
        else
            showSnackbar("Incorrect email or password.")
    }

    /**
     * Registers the account and navigates to the
     * welcome fragment if registration was successful;
     * otherwise, shows an error message.
     */
    private fun register() {
        val account = this.account
        try {
            viewModel.register(account)
            navigateToWelcomeFragment(account)
        } catch (e: RegistrationError) {
            showSnackbar(e.message)
        }
    }

    /**
     * Navigates to the welcome fragment.
     */
    private fun navigateToWelcomeFragment(account: Account) {
        findNavController().navigate(
            LoginFragmentDirections.actionLoginFragmentToWelcomeFragment(account.email)
        )
    }

    /**
     * Shows [message] in a snackbar
     * for [duration] milliseconds.
     */
    private fun showSnackbar(message: String, duration: Int = Snackbar.LENGTH_LONG) {
        Snackbar.make(requireView(), message, duration).show()
    }
}