package com.example.shoestore.shoe

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.shoestore.R
import com.example.shoestore.databinding.FragmentShoeDetailBinding

class ShoeDetailFragment : Fragment() {
    private val viewModel: ShoeViewModel by activityViewModels()

    private lateinit var binding: FragmentShoeDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_shoe_detail, container, false
        )
        binding.buttonCancel.setOnClickListener {
            // Return back to the shoe list fragment.
            findNavController().popBackStack()
        }
        binding.buttonSave.setOnClickListener {
            // Add the shoe to the list.
            viewModel.addShoe(shoe)
            findNavController().popBackStack()
        }
        return binding.root
    }

    /**
     * The shoe read from the input fields.
     */
    private val shoe: Shoe
        get() = Shoe(
            name = binding.editTextShoeName.text.toString(),
            company = binding.editTextShoeCompany.text.toString(),
            size = binding.editTextShoeSize.text.toString(),
            description = binding.editTextShoeDescription.text.toString()
        )
}