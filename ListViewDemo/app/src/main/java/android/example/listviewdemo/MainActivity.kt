package android.example.listviewdemo

import android.example.listviewdemo.databinding.ActivityMainBinding
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.databinding.DataBindingUtil

class MainActivity : AppCompatActivity() {

    private val list = mutableListOf<String>("Apple", "Banana", "Cucumber", "Parsley", "Potatoe", "Beans",
        "Bread", "Carot", "Pineapple", "Strawberry", "Blackberry", "Hazelnut", "Lemon", "Orange", "Lettuce", "Onion", "Garlic", "Cherry", "Apricot")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)

        val arrayAdapter: ArrayAdapter<String> = ArrayAdapter(this, android.R.layout.simple_list_item_1, list)

        binding.listView.adapter = arrayAdapter

    }
}