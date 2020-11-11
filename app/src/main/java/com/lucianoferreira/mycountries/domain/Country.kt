package com.lucianoferreira.mycountries.domain

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Country(
    val id: Int,
    val name: String,
    var quantity: Int = 0,
    val img: Int = 0
): Parcelable

