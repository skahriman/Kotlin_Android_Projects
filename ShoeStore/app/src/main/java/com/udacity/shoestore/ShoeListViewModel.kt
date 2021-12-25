package com.udacity.shoestore

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.models.Shoe

class ShoeListViewModel: ViewModel() {

    private var _list = MutableLiveData<MutableList<String>>()
    val list: LiveData<MutableList<String>> = _list

    private var _isUserActive = MutableLiveData<Boolean>(false);
    val isUserActive = _isUserActive

    private val shoe_1 = Shoe("Soccer Shoe", 10.0, "Adidas", "Men")
    private val shoe_2 = Shoe("Basketball Shoe", 11.0, "Nike", "Woman")
    private val shoe_3 = Shoe("Tennis Shoe", 12.0, "Adidas", "Woman")

    // initially start with default list items
    init {
        createList()
    }

    fun add(newItem: String) {
        _list.value?.add(newItem)
    }

    fun changeStatus() {
        _isUserActive.value = true
    }

    private fun createShoe(shoe: Shoe): String {
        return shoe.name + "\n" +
                shoe.size + "\n" +
                shoe.company + "\n" +
                shoe.description
    }

    private fun createList() {
        _list.value = mutableListOf()
        _list.value!!.add(createShoe(shoe_1))
        _list.value!!.add(createShoe(shoe_2))
        _list.value!!.add(createShoe(shoe_3))
    }

}
