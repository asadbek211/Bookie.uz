package com.bizmiz.bookieuz.ui.main.book_details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bizmiz.bookieuz.ui.auth.api.NetworkHelper
import com.bizmiz.bookieuz.ui.model.BookDetailsResponse
import com.bizmiz.bookieuz.utils.Resource

class BookDetailsViewModel(private val networkHelper: NetworkHelper) : ViewModel() {
    private val data: MutableLiveData<Resource<BookDetailsResponse>> = MutableLiveData()
    val detailsData: LiveData<Resource<BookDetailsResponse>>
        get() = data

    fun getDetails(
        bookId: Int
    ) {
        networkHelper.getBookDetails(bookId, {
            data.value = Resource.success(it)
        }, {
            data.value = Resource.error(it)
        })
    }
}