package com.udacity.asteroidradar.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.udacity.asteroidradar.Asteroid
import com.udacity.asteroidradar.Constants
import com.udacity.asteroidradar.PictureOfDay
import com.udacity.asteroidradar.api.NasaApi
import com.udacity.asteroidradar.api.NasaApiService
import com.udacity.asteroidradar.api.parseAsteroidsJsonResult
import kotlinx.coroutines.launch
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel : ViewModel() {
    private val API_KEY ="3DqKevLhsid80oZMvqwGETPyOY8pK5cVNuUlRXr8"
    private val START_DATE ="2022-01-04"
    private val END_DATE = "2022-01-11"

    // The internal MutableLiveData String that stores the most recent response status
    private var _status = MutableLiveData<String>()

    // The external immutable LiveData for the status String
    val status: LiveData<String>
        get() = _status

    private var _response = MutableLiveData<String>()
    val response: LiveData<String>
        get() = _response

    private var _propertyOfPictureOfDay = MutableLiveData<PictureOfDay>()
    val propertyOfPictureOfDay: LiveData<PictureOfDay>
        get() = _propertyOfPictureOfDay

    private var _asteroidData = MutableLiveData<MutableList<Asteroid>>()
    val asteroidData: LiveData<MutableList<Asteroid>>
        get() = asteroidData


    init {
        getNasaPictureOfDay()
        getAsteroidData()
    }

    private fun getNasaPictureOfDay() {
        viewModelScope.launch {
            try {
                var result = NasaApi.retrofitService.getPictureOfDayProperties(API_KEY)
                _propertyOfPictureOfDay.value = result
            } catch (e: Exception) {
                _response.value = "Failure: ${e.message}"
            }
        }
    }

    private fun getAsteroidData() {
        NasaApi.retrofitService2.getAsteroidData(START_DATE, END_DATE, API_KEY).enqueue(object: Callback<String> {
            override fun onResponse(call: Call<String>, response: Response<String>) {
                _response.value = response.body()
                val parseAsteroidsJsonResult = parseAsteroidsJsonResult(JSONObject(_response.value))
                _asteroidData.value = parseAsteroidsJsonResult
                Log.i("MainViewModel", "size is: ${_asteroidData.value!!.size}")
            }

            override fun onFailure(call: Call<String>, t: Throwable) {
                _response.value = "Failure: " + t.message
            }
        })
    }

}