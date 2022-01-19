package com.udacity.asteroidradar.main

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.udacity.asteroidradar.*
import com.udacity.asteroidradar.api.NasaApi
import com.udacity.asteroidradar.api.NasaApiService
import com.udacity.asteroidradar.api.parseAsteroidsJsonResult
import com.udacity.asteroidradar.database.getDatabase
import com.udacity.asteroidradar.repository.AsteroidsRepository
import kotlinx.coroutines.launch
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel(application: Application) : AndroidViewModel(application) {
    private val API_KEY ="3DqKevLhsid80oZMvqwGETPyOY8pK5cVNuUlRXr8"
    private val START_DATE = getDay(0).toString("yyyy-MM-dd")
    private val END_DATE = getDay(7).toString("yyyy-MM-dd")

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

    private val database = getDatabase(application)
    private val asteroidsRepository = AsteroidsRepository(database)

    init {
        viewModelScope.launch {
            getNasaPictureOfDay()
            asteroidsRepository.refreshAsteroids()
        }
    }

    val asteroidData = asteroidsRepository.asteroids

    private fun getNasaPictureOfDay() {
        viewModelScope.launch {
            try {
                var result = NasaApi.retrofitService.getPictureOfDayProperties(Constants.API_KEY)
                _propertyOfPictureOfDay.value = result
            } catch (e: Exception) {
                _response.value = "Failure: ${e.message}"
            }
        }
    }
}