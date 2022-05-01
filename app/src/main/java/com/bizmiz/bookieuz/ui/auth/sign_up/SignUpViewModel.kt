package com.bizmiz.bookieuz.ui.auth.sign_up

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bizmiz.bookieuz.ui.auth.api.NetworkHelper
import com.bizmiz.bookieuz.ui.model.Data
import com.bizmiz.bookieuz.ui.model.GenericResponse
import com.bizmiz.bookieuz.ui.model.SignUpData
import com.bizmiz.bookieuz.utils.Resource

class SignUpViewModel(private val networkHelper: NetworkHelper) : ViewModel() {
    private val data: MutableLiveData<Resource<GenericResponse<Data>>> = MutableLiveData()
    val signUpData: LiveData<Resource<GenericResponse<Data>>>
        get() = data

    fun getSignUp(signUpData: SignUpData) {
        networkHelper.signUp(signUpData, {
            data.value = Resource.success(it)
        }, {
            data.value = Resource.error(it)
        })
    }
}