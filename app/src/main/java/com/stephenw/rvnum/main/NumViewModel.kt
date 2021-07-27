package com.stephenw.rvnum.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel


class NumViewModel : ViewModel() {
    // how many items will be added at the start so we initialize a count variable
    private var countAtStart = 11

    // has to be mutable because it needs to be altered when button is clicked
    private val numList = mutableListOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)

    // initializing live data for the mutable list of numbers
    private val dataList = MutableLiveData<MutableList<Int>>()

    val data get() = dataList

    // populate the list when view model is called
    init {
        initList()
    }

    // calling when button is pushed so it cannot be private
    fun addNumToList() {
        numList.add(countAtStart)
        dataList.value = numList
        countAtStart += 1
    }

    private fun initList() {
        dataList.value = numList
    }

}