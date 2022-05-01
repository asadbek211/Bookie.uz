package com.bizmiz.bookieuz.ui.main.home.latest

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bizmiz.bookieuz.ui.auth.api.NetworkHelper
import com.bizmiz.bookieuz.ui.model.DataX
import com.bizmiz.bookieuz.ui.model.GenericResponse
import com.bizmiz.bookieuz.ui.model.Lastest
import com.bizmiz.bookieuz.utils.Resource

class LatestViewModel(private val networkHelper: NetworkHelper) : ViewModel() {
    private val data: MutableLiveData<Resource<List<Lastest>>> = MutableLiveData()
    val latestList: LiveData<Resource<List<Lastest>>>
        get() = data

    fun getLatest() {
        networkHelper.getLatestBook({
            data.value = Resource.success(it)
        }, {
            data.value = Resource.error(it)
        })
    }
}