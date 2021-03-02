package com.example.androiddevchallenge.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androiddevchallenge.datasource.PuppyRepo
import com.example.androiddevchallenge.model.Puppy
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class PuppyListViewModel : ViewModel() {

    val pups = PuppyRepo.getPuppies().onEach {
        println("FlowEmitted: $it")
    }

    fun togglePuppyAdoption(puppy: Puppy) = viewModelScope.launch {
        PuppyRepo.toggleAdoption(puppy.id)
    }

    fun seeDetails(puppy: Puppy) {
        println("seeDetails $puppy")
    }
}