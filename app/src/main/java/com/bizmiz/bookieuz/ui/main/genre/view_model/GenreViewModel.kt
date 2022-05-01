package com.bizmiz.bookieuz.ui.main.genre.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bizmiz.bookieuz.ui.auth.api.NetworkHelper
import com.bizmiz.bookieuz.ui.model.DataX
import com.bizmiz.bookieuz.ui.model.GenericResponse
import com.bizmiz.bookieuz.utils.Resource

class GenreViewModel(private val networkHelper: NetworkHelper) : ViewModel() {
    private val data: MutableLiveData<Resource<GenericResponse<List<DataX>>>> = MutableLiveData()
    val categoryList: LiveData<Resource<GenericResponse<List<DataX>>>>
        get() = data

    fun getCategory() {
        networkHelper.getCategory({
            data.value = Resource.success(it)
        }, {
            data.value = Resource.error(it)
        })
    }
}