package com.example.shoestore

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.core.view.MenuProvider
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
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
        binding.buttonBrowseShoes.setOnClickListener {
            // Navigate to the shoes list fragment.
            findNavController().navigate(
                WelcomeFragmentDirections.actionWelcomeFragmentToShoeListFragment()
            )
        }
        // Add a menu to navigate to the instructions fragment.
        // The menu is removed when this fragment's view is destroyed.
        requireActivity().addMenuProvider(menuProvider, viewLifecycleOwner)
        return binding.root
    }

    /**
     * A menu provider used to show the instructions menu.
     */
    private val menuProvider = object : MenuProvider {
        override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
            menuInflater.inflate(R.menu.instructions_menu, menu)
        }

        override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
            // Navigatie to the instructions fragment.
            return NavigationUI.onNavDestinationSelected(menuItem, findNavController())
        }
    }
}