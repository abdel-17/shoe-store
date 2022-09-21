package com.example.shoestore.shoe

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ShoeViewModel : ViewModel() {
    /**
     * The list of shoes added by the user.
     */
    private var shoesList = mutableListOf<Shoe>()

    /**
     * The private variant of [shoes] used to update
     * its value whenever [shoesList] changes.
     *
     * - Note: Due to how generics work in java,
     * `LiveData<MutableList<Shoe>>` is not a
     * subclass of `LiveData<List<Shoe>>`,
     * so we need a separate [shoesList] variable
     * to mutate the list.
     */
    private var _shoes = MutableLiveData<List<Shoe>>()

    /**
     * The list of shoes added by the user.
     *
     * When a new value is added to this list,
     * its observers will be notified.
     */
    val shoes: LiveData<List<Shoe>>
        get() = _shoes

    fun addShoe(shoe: Shoe) {
        shoesList.add(shoe)
        // Update the value to notifiy observers.
        _shoes.value = shoesList
    }
}