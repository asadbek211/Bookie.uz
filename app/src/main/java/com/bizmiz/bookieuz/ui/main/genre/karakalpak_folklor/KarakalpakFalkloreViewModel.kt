package com.bizmiz.bookieuz.ui.main.genre.karakalpak_folklor

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bizmiz.bookieuz.ui.auth.api.NetworkHelper
import com.bizmiz.bookieuz.ui.model.CategoryBooks
import com.bizmiz.bookieuz.ui.model.DataX
import com.bizmiz.bookieuz.ui.model.GenericResponse
import com.bizmiz.bookieuz.utils.Resource

class KarakalpakFalkloreViewModel(private val networkHelper: NetworkHelper) : ViewModel() {
    private val data: MutableLiveData<Resource<CategoryBooks>> = MutableLiveData()
    val categoryData: LiveData<Resource<CategoryBooks>>
        get() = data
    private val data2: MutableLiveData<Resource<List<String>>> = MutableLiveData()
    val subcategoryData: LiveData<Resource<List<String>>>
        get() = data2
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
    fun getSubcategory(
    ) {
        networkHelper.getSubcategory({
            data2.value = Resource.success(it)
        }, {
            data2.value = Resource.error(it)
        })
    }
}