package com.example.shoestore.onboarding

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.example.shoestore.R
import com.example.shoestore.databinding.FragmentWelcomeBinding

class WelcomeFragment : Fragment() {
    private lateinit var binding: FragmentWelcomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?,
    ): View {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_welcome, container, false
        )
        // Initialize the email recieved from the login fragment.
        binding.email = WelcomeFragmentArgs.fromBundle(requireArguments()).email
        // Setup the browse shoes button action.
        binding.buttonShowInstructions.setOnClickListener {
            // Navigate to the instructions fragment.
            findNavController().navigate(
                WelcomeFragmentDirections.actionWelcomeFragmentToInstructionsFragment()
            )
        }
        return binding.root
    }
}