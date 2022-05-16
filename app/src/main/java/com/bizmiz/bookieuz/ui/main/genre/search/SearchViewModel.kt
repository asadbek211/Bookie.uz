package com.bizmiz.bookieuz.ui.main.genre.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bizmiz.bookieuz.ui.auth.api.NetworkHelper
import com.bizmiz.bookieuz.ui.model.BookDetails
import com.bizmiz.bookieuz.utils.Resource

class SearchViewModel(private val networkHelper: NetworkHelper) : ViewModel() {
    private val data: MutableLiveData<Resource<List<BookDetails>>> = MutableLiveData()
    val searchData: LiveData<Resource<List<BookDetails>>>
        get() = data

    fun getSearchData(
        query: String
    ) {
        networkHelper.getQueryData(query, {
            data.value = Resource.success(it)
        }, {
            data.value = Resource.error(it)
        })
    }
}