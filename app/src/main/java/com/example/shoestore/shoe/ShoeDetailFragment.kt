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
        binding.shoe = Shoe()
        binding.buttonCancel.setOnClickListener {
            // Return back to the shoe list fragment.
            findNavController().popBackStack()
        }
        binding.buttonSave.setOnClickListener {
            // Add the shoe to the list.
            binding.shoe?.let { shoe ->
                viewModel.addShoe(shoe)
                findNavController().popBackStack()
            }
        }
        return binding.root
    }
}