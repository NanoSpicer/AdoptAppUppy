package com.example.androiddevchallenge.model

import android.os.Parcelable
import androidx.annotation.DrawableRes
import kotlinx.parcelize.Parcelize


internal var IDS = 0L

fun Puppy(name: String, tagline: String = "", race: String, @DrawableRes image: Int, adopted: Boolean = false): Puppy =
    Puppy(++IDS, name, tagline, race, image, adopted)


@Parcelize
data class Puppy internal constructor(
    val id: Long,
    val name: String,
    val tagline: String = "",
    val race: String,
    @DrawableRes val image: Int,
    var adopted: Boolean = false
) : Parcelable

