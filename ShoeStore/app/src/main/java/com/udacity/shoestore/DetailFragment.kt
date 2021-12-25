package com.udacity.shoestore

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat.getSystemService
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.udacity.shoestore.databinding.FragmentDetailBinding
import com.udacity.shoestore.databinding.FragmentShoeListBinding
import com.udacity.shoestore.models.Shoe
import android.app.Activity




class DetailFragment : Fragment() {
    private lateinit var binding: FragmentDetailBinding
    private val sharedViewModel: ShoeListViewModel by activityViewModels()
    private lateinit var shoe: Shoe

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_detail, container, false)


        binding.btnAdd.setOnClickListener { view: View ->

            val name = binding.name.text.toString()
            val size = binding.size.text.toString().toDouble()
            val company = binding.company.text.toString()
            val description = binding.description.text.toString()

            shoe = Shoe(name, size, company, description)
            binding.shoeData = shoe

            sharedViewModel.add(createShoe(shoe))

            hideKeyboard(context!!, view)

            view.findNavController().navigate(R.id.action_detailFragment_to_shoeListFragment)
        }

        binding.btnCancel.setOnClickListener { view: View ->
            hideKeyboard(context!!, view)
            view.findNavController().navigate(R.id.action_detailFragment_to_shoeListFragment)
        }

        return binding.root
    }


    private fun createShoe(shoe: Shoe): String {
        return shoe.name + "\n" +
                shoe.size + "\n" +
                shoe.company + "\n" +
                shoe.description
    }

    // Change the Title for the fragment
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity).supportActionBar?.title="Detail"
    }

    fun hideKeyboard(context: Context, view: View) {
        val imm = context.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }

}
