package android.example.recyclerviewdemo

import android.example.recyclerviewdemo.databinding.ActivityMainBinding
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager

class MainActivity : AppCompatActivity() {

    val list = mutableListOf("1", "2", "3", "4", "5", "6", "7", "8", "9", "1", "2", "3", "4", "5", "6", "7", "8", "9", "1", "2", "3", "4", "5", "6", "7", "8", "9")
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        val adapter = MyAdapter(list)
        binding.recyclerView.adapter = adapter

        //added this
        adapter.setOnItemClickListener(object : MyAdapter.MyClickListener {
            override fun onItemClick(position: Int) {
                Log.i("MainActivity", "${position}")
                Toast.makeText(applicationContext, "You clicked ", Toast.LENGTH_LONG).show();
            }

        })
        // added this


        binding.recyclerView.layoutManager = LinearLayoutManager(this)





    }
}