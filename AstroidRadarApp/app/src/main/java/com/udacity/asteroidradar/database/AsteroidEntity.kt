package com.udacity.asteroidradar.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "asteroid_table")
data class AsteroidEntity(
    @PrimaryKey
    val id: Long,

    @ColumnInfo(name = "code_name")
    val codename: String,

    @ColumnInfo(name = "close_approach_date")
    val closeApproachDate: String,

    @ColumnInfo(name = "absolute_magnitude")
    val absoluteMagnitude: Double,

    @ColumnInfo(name = "estimated_diameter")
    val estimatedDiameter: Double,

    @ColumnInfo(name = "relative_velocity")
    val relativeVelocity: Double,

    @ColumnInfo(name = "distance_fromEarth")
    val distanceFromEarth: Double,

    @ColumnInfo(name = "is_potentially_hazardous")
    val isPotentiallyHazardous: Boolean
)
