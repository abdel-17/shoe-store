package com.example.shoestore.shoe

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.R.style.*
import androidx.fragment.app.Fragment
import androidx.core.view.MenuProvider
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.shoestore.R
import com.example.shoestore.databinding.FragmentShoeListBinding

class ShoeListFragment : Fragment() {
    private val viewModel: ShoeViewModel by activityViewModels()

    private lateinit var binding: FragmentShoeListBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?,
    ): View {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_shoe_list, container, false
        )
        viewModel.shoes.observe(viewLifecycleOwner) { shoeList ->
            Log.d("Shoe List Changed", "shoeList has changed to $shoeList.")
            // A new shoe has been added, so the shoe list is no longer empty.
            binding.textAddShoes.visibility = View.GONE
            // Add the shoes to the linear layout.
            shoeList.forEach(this::addShoe)
        }
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

    /**
     * Adds text views describing [shoe]
     * to the shoe list's layout.
     */
    private fun addShoe(shoe: Shoe) {
        val layout = binding.linearLayoutShoeList
        val regularSpacing = resources.getDimensionPixelSize(R.dimen.regular_spacing)
        val smallSpacing = resources.getDimensionPixelSize(R.dimen.small_spacing)
        val matchParent = LinearLayout.LayoutParams.MATCH_PARENT
        // Add the name with a title style.
        val nameView = TextView(context).apply {
            layoutParams = LinearLayout.LayoutParams(matchParent, matchParent).apply {
                // Add spacing between the name and the text above.
                topMargin = regularSpacing
            }
            text = shoe.name
            setTextAppearance(TextAppearance_AppCompat_Title)
        }
        layout.addView(nameView)
        // Add the rest of the shoe properties with a headline style.
        val propertiesView = TextView(context).apply {
            layoutParams = LinearLayout.LayoutParams(matchParent, matchParent).apply {
                // Add smaller spacing between the properties and the name.
                topMargin = smallSpacing
                // Add a bottom margin to keep some space between
                // the last element and the bottom of the device.
                bottomMargin = regularSpacing
            }
            // Company: ${shoe.company}
            // Size: ${shoe.company}
            // Description: ${shoe.description}
            text = getString(R.string.shoe_details_format, shoe.company, shoe.size, shoe.description)
            setTextAppearance(TextAppearance_AppCompat_Subhead)
            // Add a line spacing multiplier to improve readability.
            setLineSpacing(0F, 1.2F)
        }
        layout.addView(propertiesView)
    }
}