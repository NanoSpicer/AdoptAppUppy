package com.example.androiddevchallenge.viewmodel

import androidx.lifecycle.ViewModel
import com.example.androiddevchallenge.datasource.PuppyRepo
import kotlinx.coroutines.flow.map


class PuppyDetailsViewModel(val puppyId: Long) : ViewModel() {

    val puppy = PuppyRepo.getPuppy(puppyId).map { it }

}