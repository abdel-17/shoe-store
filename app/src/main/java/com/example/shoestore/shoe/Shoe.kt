package com.example.shoestore.shoe

import androidx.lifecycle.MutableLiveData

data class Shoe(
    val name: MutableLiveData<String> = MutableLiveData(),
    val company: MutableLiveData<String> = MutableLiveData(),
    val size: MutableLiveData<String> = MutableLiveData(),
    val description: MutableLiveData<String> = MutableLiveData())