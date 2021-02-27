package com.example.androiddevchallenge

import com.example.androiddevchallenge.model.Puppy
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*


object PuppyRepo {
    private val changeFlow = MutableStateFlow(Unit)
    private val pups: List<Puppy>
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
        pups = puppyNames.map { name ->
            val nTags = (1..3).random()
            val tagline = puppyTags.shuffled().take(nTags).joinToString()
            val breed = puppyBreeds.random()
            Puppy(name, tagline, breed)
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


    private fun Puppy.toggleAdoption() { adopted = !adopted }

}