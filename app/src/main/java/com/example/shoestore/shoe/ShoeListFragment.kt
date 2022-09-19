package com.example.shoestore.shoe

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.core.view.MenuProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.shoestore.R

class ShoeListFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?,
    ): View? {
        // TODO setup layout
        // Add a menu to logout of the account.
        requireActivity().addMenuProvider(menuProvider, viewLifecycleOwner)
        return inflater.inflate(R.layout.fragment_shoe_list, container, false)
    }

    /**
     * The menu provider of the logout menu.
     */
    private val menuProvider = object : MenuProvider {
        override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
            menuInflater.inflate(R.menu.logout_menu, menu)
        }

        override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
            // Navigate to the login fragment.
            findNavController().navigate(
                ShoeListFragmentDirections.actionShoeListFragmentToLoginFragment()
            )
            return true
        }
    }
}