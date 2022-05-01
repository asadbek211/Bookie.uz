package com.bizmiz.bookieuz.ui.main.home.views

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bizmiz.bookieuz.ui.auth.api.NetworkHelper
import com.bizmiz.bookieuz.ui.model.View
import com.bizmiz.bookieuz.utils.Resource

class ViewsBookViewModel(private val networkHelper: NetworkHelper) : ViewModel() {
    private val data: MutableLiveData<Resource<List<View>>> = MutableLiveData()
    val viewList: LiveData<Resource<List<View>>>
        get() = data

    fun getViews() {
        networkHelper.getViewsBook({
            data.value = Resource.success(it)
        }, {
            data.value = Resource.error(it)
        })
    }
}