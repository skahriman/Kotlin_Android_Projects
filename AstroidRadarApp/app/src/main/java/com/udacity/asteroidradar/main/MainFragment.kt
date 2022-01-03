package com.udacity.asteroidradar.main

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.udacity.asteroidradar.Asteroid
import com.udacity.asteroidradar.R
import com.udacity.asteroidradar.adapter.MyAdapter
import com.udacity.asteroidradar.databinding.FragmentMainBinding

class MainFragment : Fragment() {

//    val list = mutableListOf("1", "2", "3", "4", "5", "6", "7", "8", "9", "1", "2", "3", "4", "5", "6", "7", "8", "9", "1", "2", "3", "4", "5", "6", "7", "8", "9")
    val list = listOf(Asteroid(
    1, "code1",
    "2022-01-01",
    1.0,
    1.0,
    1.0,
    1.0,
    false ))

    private val viewModel: MainViewModel by lazy {
        ViewModelProvider(this).get(MainViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val binding = FragmentMainBinding.inflate(inflater)
        binding.lifecycleOwner = this

        binding.viewModel = viewModel

        setHasOptionsMenu(true)

        val adapter = MyAdapter(list)

        binding.asteroidRecycler.adapter = adapter

        //for listener
        adapter.setOnItemClickListener(object : MyAdapter.MyClickListener {
            override fun onItemClick(position: Int) {
                Log.i("MainFragment", "${position}")
                Toast.makeText(context, "You clicked ", Toast.LENGTH_LONG).show();
            }

        })
        // for listener

        binding.asteroidRecycler.layoutManager = LinearLayoutManager(context)

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
