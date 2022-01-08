package com.udacity.asteroidradar.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface AsteroidDatabaseDao {
    @Insert
    fun insert(asteroidEntity: AsteroidEntity)

    @Update
    fun update(asteroidEntity: AsteroidEntity)

    @Query("SELECT * FROM asteroid_table WHERE id = :key")
    fun get(key: Long): AsteroidEntity

    @Query("SELECT * FROM asteroid_table ORDER BY id DESC LIMIT 1")
    fun getAsteroid(): AsteroidEntity
}