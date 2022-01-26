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
import java.net.URLConnection
import java.security.cert.CertPath

class MainViewModel(application: Application) : AndroidViewModel(application) {

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
            try {
                asteroidsRepository.refreshAsteroids()
            } catch (e: Exception) {
                _response.value = "Failure: ${e.message}"
            }
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