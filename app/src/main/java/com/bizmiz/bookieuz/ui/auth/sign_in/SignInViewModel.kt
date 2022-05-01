package com.bizmiz.bookieuz.ui.auth.sign_in

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bizmiz.bookieuz.ui.auth.api.NetworkHelper
import com.bizmiz.bookieuz.ui.model.Data
import com.bizmiz.bookieuz.ui.model.GenericResponse
import com.bizmiz.bookieuz.ui.model.SignInData
import com.bizmiz.bookieuz.utils.Resource

class SignInViewModel(private val networkHelper: NetworkHelper) : ViewModel() {
    private val data: MutableLiveData<Resource<GenericResponse<Data>>> = MutableLiveData()
    val signInData: LiveData<Resource<GenericResponse<Data>>>
        get() = data

    fun getSignIn(signInData: SignInData) {
        networkHelper.signIn(signInData, {
            data.value = Resource.success(it)
        }, {
            data.value = Resource.error(it)
        })
    }
}