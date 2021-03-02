package com.example.androiddevchallenge.datasource

import com.example.androiddevchallenge.R
import com.example.androiddevchallenge.model.Puppy
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*


object PuppyRepo {
    private val changeFlow = MutableStateFlow(Unit)
    private val pups: List<Puppy>

    private val puppyImages = listOf(
        R.drawable._1,
        R.drawable._2,
        R.drawable._3,
        R.drawable._4,
        R.drawable._5,
        R.drawable._6,
        R.drawable._7,
        R.drawable._8,
        R.drawable._9,
        R.drawable._10,
        R.drawable._11,
        R.drawable._12,
        R.drawable._13,
        R.drawable._14,
        R.drawable._15,
        R.drawable._16,
        R.drawable._17,
        R.drawable._18,
        R.drawable._19,
    )


    private val puppyNames = listOf(
        "Gordie",
        "Alice",
        "Belle",
        "Olivia",
        "Bubba",
        "Pandora",
        "Bailey",
        "Nala",
        "Rosco",
        "Butch",
        "Matilda",
        "Molly",
        "Piper",
        "Kelsey",
        "Rufus",
        "Duke",
        "Ozzy"
    )

    private val puppyTags = listOf(
        "doggo",
        "doge",
        "special dogo",
        "wrinkler",
        "corgo",
        "shoob",
        "puggo",
        "pupper",
        "small dogo",
        "big ol dogo",
        "woofer",
        "floofer",
        "yapper",
        "pupper",
        "good-boye",
        "grizlord",
        "snip-snap dogo"
    )

    private val puppyBreeds = listOf(
        "Labrador Retriever",
        "German Shepard",
        "Golden Retriever",
        "French Bulldog",
        "Bulldog",
        "Beagle",
        "Poodle",
        "Rottweiler",
        "German Shorthaired Pointer",
        "Yorkshire Terrier",
        "Boxer"
    )

    init {
        pups = puppyImages.map { image ->
            val name = puppyNames.random()
            val tagline = puppyTags.random()
            val breed = puppyBreeds.random()
            Puppy(name, tagline, breed, image)
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    fun getPuppies() = changeFlow.flatMapLatest {
        flow {
            delay(300)
            emit(pups)
        }
    }

    fun getPuppy(puppyId: Long) = flow {
        emit(pups.find { it.id == puppyId })
    }


    suspend fun toggleAdoption(puppyId: Long): Boolean {
        val found = getPuppy(puppyId).first()?.toggleAdoption()?.let { true } ?: false
        if (found) {
            // Trigger a new emission for those that are consuming a Flow from getPuppies
            changeFlow.value = Unit
        }
        return found
    }


    private fun Puppy.toggleAdoption() {
        adopted = !adopted
    }

}