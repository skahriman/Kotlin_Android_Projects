package android.example.recyclerviewdemo

import android.example.recyclerviewdemo.databinding.ActivityMainBinding
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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

        // comment this below and uncomment line#25 and 26 to use grid layout
        binding.recyclerView.layoutManager = LinearLayoutManager(this)

//        val manager = GridLayoutManager(this, 3)
//        binding.recyclerView.layoutManager = manager


    }
}