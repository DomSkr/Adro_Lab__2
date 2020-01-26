package com.kubickiengineering.laboratorium__2

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainActivityViewModel : ViewModel() {

    val d = MutableLiveData<String>("")

    fun getMoreData() {
        d.value = d.value + "SOME OTHER STRING ...."
        println("DATA: ${d.value}")
    }
}