package com.udacity.shoestore

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.udacity.shoestore.databinding.FragmentShoeListBinding
import com.udacity.shoestore.models.Shoe

class ShoeListFragment : Fragment() {
    private val sharedViewModel: ShoeListViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding: FragmentShoeListBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_shoe_list, container, false)

        sharedViewModel.list.observe(viewLifecycleOwner, Observer { newList ->

        })

        binding.btnAddShoe.setOnClickListener { view: View ->
            view.findNavController().navigate(R.id.action_shoeListFragment_to_detailFragment)
        }

        sharedViewModel.list.observe(viewLifecycleOwner, Observer { newCount ->
            val arrayAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, newCount)
            binding.listView.adapter = arrayAdapter
        })

        return binding.root
    }

    // Change the Title for the fragment
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity).supportActionBar?.title="Shoe List"
    }


}