package com.example.androiddevchallenge.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androiddevchallenge.datasource.PuppyRepo
import com.example.androiddevchallenge.model.Puppy
import kotlinx.coroutines.launch

class PuppyListViewModel : ViewModel() {

    val pups = PuppyRepo.getPuppies()

    fun togglePuppyAdoption(puppy: Puppy) = viewModelScope.launch {
        PuppyRepo.toggleAdoption(puppy.id)
    }

    fun seeDetails(puppy: Puppy) {

    }
}