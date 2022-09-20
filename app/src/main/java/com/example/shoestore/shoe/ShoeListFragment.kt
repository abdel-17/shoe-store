package com.example.shoestore.shoe

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.core.view.MenuProvider
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.example.shoestore.R
import com.example.shoestore.databinding.FragmentShoeListBinding

class ShoeListFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?,
    ): View {
        // TODO setup the rest of the layout
        val binding: FragmentShoeListBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_shoe_list, container, false
        )
        binding.buttonAddShoe.setOnClickListener {
            // Navigate to the shoe detail fragment.
            findNavController().navigate(
                ShoeListFragmentDirections.actionShoeListFragmentToShoeDetailFragment()
            )
        }
        // Add a menu to logout of the account. This menu is tied to the
        // view lifecycle, so it's destroyed when the view is destroyed.
        activity?.addMenuProvider(menuProvider, viewLifecycleOwner)
        return binding.root
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