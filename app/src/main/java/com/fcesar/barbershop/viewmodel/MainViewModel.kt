package com.fcesar.barbershop.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.fcesar.barbershop.model.BarberItem
import com.fcesar.barbershop.repository.BarberRepository

class MainViewModel : ViewModel() {

    private val _barberItems = MutableLiveData<List<BarberItem>>()

    val barberItems: LiveData<List<BarberItem>>
        get() = _barberItems

    init {
        loadBarberItems()
    }

    private fun loadBarberItems() {
        val repository = BarberRepository()
        _barberItems.value = repository.getBarberItems()
    }
}