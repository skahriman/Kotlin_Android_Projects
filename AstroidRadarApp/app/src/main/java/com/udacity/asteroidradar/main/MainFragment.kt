package com.udacity.asteroidradar.main

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.udacity.asteroidradar.Asteroid
import com.udacity.asteroidradar.R
import com.udacity.asteroidradar.adapter.MyAdapter
import com.udacity.asteroidradar.databinding.FragmentMainBinding

class MainFragment : Fragment() {

    private val viewModel: MainViewModel by lazy {
        ViewModelProvider(this).get(MainViewModel::class.java)
    }
    private lateinit var myAdapter: MyAdapter
    private lateinit var selectedAsteroid: Asteroid

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val binding = FragmentMainBinding.inflate(inflater)
        binding.lifecycleOwner = this

        binding.viewModel = viewModel

        setHasOptionsMenu(true)

        viewModel.asteroidData.observe(viewLifecycleOwner, Observer { response ->
            myAdapter = viewModel.asteroidData.value?.let { MyAdapter(it) }!!
            binding.asteroidRecycler.adapter = myAdapter
            //for listener
            myAdapter.setOnItemClickListener(object : MyAdapter.MyClickListener {
                override fun onItemClick(position: Int) {
                    selectedAsteroid = viewModel.asteroidData.value!![position]
                    val view: View = binding.asteroidRecycler.findViewHolderForAdapterPosition(position)!!.itemView
                    view.findNavController().navigate(MainFragmentDirections.actionShowDetail(selectedAsteroid))

                }

            })
            // for listener
        })



        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main_overflow_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return true
    }

}
