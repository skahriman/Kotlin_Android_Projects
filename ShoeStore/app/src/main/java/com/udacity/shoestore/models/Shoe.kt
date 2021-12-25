package com.udacity.shoestore.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

//@Parcelize
//data class Shoe(var name: String,
//                var size: Double,
//                var company: String,
//                var description: String,
//                val images: List<String> = mutableListOf() //Not sure how to use this
//) : Parcelable

@Parcelize
data class Shoe(var name: String,
                var size: Double,
                var company: String,
                var description: String
) : Parcelable