package com.udacity.asteroidradar.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.udacity.asteroidradar.Constants
import com.udacity.asteroidradar.PictureOfDay
import com.udacity.asteroidradar.api.NasaApi
import com.udacity.asteroidradar.api.NasaApiService
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel : ViewModel() {
    private val API_KEY ="3DqKevLhsid80oZMvqwGETPyOY8pK5cVNuUlRXr8"
    private val _response = MutableLiveData<String>()
    val response: LiveData<String>
        get() = _response

    private val _propertyOfPictureOfDay = MutableLiveData<PictureOfDay>()
    val propertyOfPictureOfDay: LiveData<PictureOfDay>
        get() = _propertyOfPictureOfDay

    init {
        getNasaPictureOfDay()
    }

    private fun getNasaPictureOfDay() {
        viewModelScope.launch {
            try {
                var listResult = NasaApi.retrofitService.getPictureOfDayProperties(API_KEY)
                _response.value = "Success: ${listResult} Mars properties retrieved"
            } catch (e: Exception) {
                _response.value = "Failure: ${e.message}"
            }
        }
    }
}