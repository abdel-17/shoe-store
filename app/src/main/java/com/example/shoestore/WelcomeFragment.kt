package com.example.shoestore

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.core.view.MenuProvider
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Lifecycle
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.shoestore.databinding.FragmentWelcomeBinding

class WelcomeFragment : Fragment() {
    private lateinit var binding: FragmentWelcomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?,
    ): View {
        // Inflate the layout of this fragment.
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_welcome, container, false
        )

        // Add a menu to navigate to the instructions fragment.
        val menuProvider = object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.overflow_menu, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem) =
                NavigationUI.onNavDestinationSelected(menuItem, findNavController())
        }
        // Tie the menu to the view lifecycle to remove it when it's destroyed.
        requireActivity().addMenuProvider(
            menuProvider, viewLifecycleOwner, Lifecycle.State.RESUMED
        )
        return binding.root
    }
}