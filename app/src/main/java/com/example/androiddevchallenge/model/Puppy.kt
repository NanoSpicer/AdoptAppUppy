package com.example.androiddevchallenge.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


internal var IDS = 0L

fun Puppy(name: String, tagline: String = "", race: String, imageUrl: String? = null, adopted: Boolean = false): Puppy =
    Puppy(++IDS, name, tagline, race, imageUrl, adopted)


@Parcelize
data class Puppy internal constructor(
    val id: Long,
    val name: String,
    val tagline: String = "",
    val race: String,
    val imageUrl: String? = null,
    var adopted: Boolean = false
) : Parcelable

