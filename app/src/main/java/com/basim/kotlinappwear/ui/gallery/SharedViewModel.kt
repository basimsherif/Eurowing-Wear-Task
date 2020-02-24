package com.basim.kotlinapp.ui.gallery

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.basim.kotlinapp.data.model.Gallery

class SharedViewModel : ViewModel() {
    val selected = MutableLiveData<Gallery>()

    fun select(item: Gallery) {
        selected.value = item
    }
}