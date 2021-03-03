/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.androiddevchallenge.datasource

import com.example.androiddevchallenge.R
import com.example.androiddevchallenge.model.Puppy
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf

object PuppyRepo {
    private val changeFlow = MutableStateFlow(0)
    private var pups: List<Puppy>

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
        "German Pointer",
        "Yorkshire Terrier",
        "Boxer"
    )

    init {
        pups = puppyImages.map { image ->
            val name = puppyNames.random()
            val takeN = (2..5).random()
            val tagline = puppyTags.toSet().toList().shuffled().take(takeN).joinToString(" ") { "#$it" }
            val breed = puppyBreeds.random()
            Puppy(name, tagline, breed, image)
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    fun getPuppies() = changeFlow.flatMapLatest { flowOf(pups) }

    fun getPuppy(puppyId: Long) = flow {
        emit(pups.find { it.id == puppyId })
    }

    suspend fun toggleAdoption(puppyId: Long): Boolean {
        var found = false
        pups = pups.map {
            val isThePuppy = it.id == puppyId
            found = found || isThePuppy
            if (isThePuppy) it.copy(adopted = !it.adopted) else it.copy()
        }
        if (found) {
            // Trigger a new emission for those that are consuming a Flow from getPuppies
            changeFlow.value = changeFlow.value + 1
        }
        return found
    }
}
