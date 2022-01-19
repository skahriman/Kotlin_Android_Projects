package com.udacity.asteroidradar.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.map
import com.udacity.asteroidradar.Asteroid
import com.udacity.asteroidradar.api.NasaApi
import com.udacity.asteroidradar.api.parseAsteroidsJsonResult
import com.udacity.asteroidradar.database.AsteroidDatabase
import com.udacity.asteroidradar.database.AsteroidEntity
import com.udacity.asteroidradar.database.asDomainModel
import com.udacity.asteroidradar.getDay
import com.udacity.asteroidradar.network.NetworkAsteroidContainer
import com.udacity.asteroidradar.network.asDatabaseModel
import com.udacity.asteroidradar.toString
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONObject
import retrofit2.await

class AsteroidsRepository(private val database: AsteroidDatabase) {
    val asteroids: LiveData<List<Asteroid>> =
        Transformations.map(database.asteroidDao.getAsteroids()) {
            it.asDomainModel()
        }

    suspend fun refreshAsteroids() {
        withContext(Dispatchers.IO) {
            val asteroidListString = NasaApi.retrofitService2.getAsteroidData(
                getDay(0).toString("yyyy-MM-dd"),
                getDay(7).toString("yyyy-MM-dd"),
                "3DqKevLhsid80oZMvqwGETPyOY8pK5cVNuUlRXr8").await()
            val asteroidList = parseAsteroidsJsonResult(JSONObject(asteroidListString))
            val container = NetworkAsteroidContainer(asteroidList.toList())
            database.asteroidDao.insertAll(*container.asDatabaseModel())

        }
    }
}