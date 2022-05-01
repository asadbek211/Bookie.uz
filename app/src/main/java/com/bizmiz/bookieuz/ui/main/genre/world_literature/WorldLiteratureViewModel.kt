package com.bizmiz.bookieuz.ui.main.genre.world_literature

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bizmiz.bookieuz.ui.auth.api.NetworkHelper
import com.bizmiz.bookieuz.ui.model.CategoryBooks
import com.bizmiz.bookieuz.ui.model.DataX
import com.bizmiz.bookieuz.ui.model.GenericResponse
import com.bizmiz.bookieuz.utils.Resource

class WorldLiteratureViewModel(private val networkHelper: NetworkHelper) : ViewModel() {
    private val data: MutableLiveData<Resource<CategoryBooks>> = MutableLiveData()
    val categoryData: LiveData<Resource<CategoryBooks>>
        get() = data

    fun getCategoryDataByPage(
        categoryId:Int,
        page:Int,
    ) {
        networkHelper.getCategoryDataByPage(categoryId,page,{
            data.value = Resource.success(it)
        }, {
            data.value = Resource.error(it)
        })
    }
}