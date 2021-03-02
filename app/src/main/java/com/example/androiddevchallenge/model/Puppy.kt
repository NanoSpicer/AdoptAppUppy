package com.example.androiddevchallenge.model

import android.os.Parcelable
import androidx.annotation.DrawableRes
import kotlinx.parcelize.Parcelize


internal var IDS = 0L

data class Puppy (
    val name: String,
    val tagline: String = "",
    val race: String,
    @DrawableRes val image: Int,
    var adopted: Boolean = false,
    val id: Long = ++IDS,
)

