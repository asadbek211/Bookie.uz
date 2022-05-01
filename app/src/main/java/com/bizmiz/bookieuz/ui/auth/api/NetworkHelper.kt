package com.bizmiz.bookieuz.ui.auth.api

import android.util.Log
import com.bizmiz.bookieuz.ui.model.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

class NetworkHelper(private val apiClient: Retrofit) {
    fun signUp(
        signUpData: SignUpData,
        onSuccess: (response: GenericResponse<Data>) -> Unit,
        onFailure: (msg: String?) -> Unit
    ) {
        val call = apiClient.create(ApiInterface::class.java).signUp(signUpData)
        call.enqueue(object : Callback<GenericResponse<Data>> {
            override fun onResponse(
                call: Call<GenericResponse<Data>>,
                response: Response<GenericResponse<Data>>
            ) {
                CoroutineScope(Dispatchers.IO).launch {
                    val responseData = response.body()
                    withContext(Dispatchers.Main) {
                        if (responseData != null) {
                            if (responseData.success) {
                                onSuccess.invoke(responseData)
                            } else {
                                onFailure.invoke(responseData.message)
                            }
                        }
                    }
                }
            }

            override fun onFailure(call: Call<GenericResponse<Data>>?, t: Throwable?) {
                onFailure.invoke(t?.localizedMessage)
            }

        })
    }

    fun signIn(
        signInData: SignInData,
        onSuccess: (response: GenericResponse<Data>) -> Unit,
        onFailure: (msg: String?) -> Unit
    ) {
        val call = apiClient.create(ApiInterface::class.java).signIn(signInData)
        call.enqueue(object : Callback<GenericResponse<Data>> {
            override fun onResponse(
                call: Call<GenericResponse<Data>>,
                response: Response<GenericResponse<Data>>
            ) {
                CoroutineScope(Dispatchers.IO).launch {
                    val responseData = response.body()
                    withContext(Dispatchers.Main) {
                        if (responseData != null) {
                            if (responseData.success) {
                                onSuccess.invoke(responseData)
                            } else {
                                onFailure.invoke(responseData.message)
                            }
                        }
                    }
                }
            }
            override fun onFailure(call: Call<GenericResponse<Data>>?, t: Throwable?) {
                onFailure.invoke(t?.message)
            }

        })
    }

    fun getCategory(
        onSuccess: (response: GenericResponse<List<DataX>>) -> Unit,
        onFailure: (msg: String?) -> Unit
    ) {
        val call = apiClient.create(ApiInterface::class.java).getCategory()
        call.enqueue(object : Callback<GenericResponse<List<DataX>>> {
            override fun onResponse(
                call: Call<GenericResponse<List<DataX>>>,
                response: Response<GenericResponse<List<DataX>>>
            ) {
                CoroutineScope(Dispatchers.IO).launch {
                    val responseData = response.body()
                    withContext(Dispatchers.Main) {
                        if (responseData != null) {
                            onSuccess.invoke(responseData)
                        }
                    }
                }
            }

            override fun onFailure(call: Call<GenericResponse<List<DataX>>>?, t: Throwable?) {
                onFailure.invoke(t?.message)
            }

        })
    }

    fun getLatestBook(
        onSuccess: (response: List<Lastest>) -> Unit,
        onFailure: (msg: String?) -> Unit
    ) {
        val call = apiClient.create(ApiInterface::class.java).getLatest()
        call.enqueue(object : Callback<GenericResponse<LatestData>> {
            override fun onResponse(
                call: Call<GenericResponse<LatestData>>,
                response: Response<GenericResponse<LatestData>>
            ) {
                CoroutineScope(Dispatchers.IO).launch {
                    val responseData = response.body()?.data?.lastest
                    withContext(Dispatchers.Main) {
                        if (responseData != null) {
                            onSuccess.invoke(responseData)
                        }
                    }
                }
            }
            override fun onFailure(call: Call<GenericResponse<LatestData>>?, t: Throwable?) {
                onFailure.invoke(t?.message)
            }

        })
    }
    fun getViewsBook(
        onSuccess: (response: List<View>) -> Unit,
        onFailure: (msg: String?) -> Unit
    ) {
        val call = apiClient.create(ApiInterface::class.java).getLatest()
        call.enqueue(object : Callback<GenericResponse<LatestData>> {
            override fun onResponse(
                call: Call<GenericResponse<LatestData>>,
                response: Response<GenericResponse<LatestData>>
            ) {
                CoroutineScope(Dispatchers.IO).launch {
                    val responseData = response.body()?.data?.views
                    withContext(Dispatchers.Main) {
                        if (responseData != null) {
                            onSuccess.invoke(responseData)
                        }
                    }
                }
            }
            override fun onFailure(call: Call<GenericResponse<LatestData>>?, t: Throwable?) {
                onFailure.invoke(t?.message)
            }

        })
    }
    fun getCategoryDataByPage(
        categoryId:Int,
        page:Int,
        onSuccess: (response: CategoryBooks) -> Unit,
        onFailure: (msg: String?) -> Unit
    ) {
        val call = apiClient.create(ApiInterface::class.java).getCategoryDataByPage(categoryId,page)
        call.enqueue(object : Callback<GenericResponse<CategoryBooks>> {
            override fun onResponse(
                call: Call<GenericResponse<CategoryBooks>>,
                response: Response<GenericResponse<CategoryBooks>>
            ) {
                CoroutineScope(Dispatchers.IO).launch {
                    val responseData = response.body()?.data
                    withContext(Dispatchers.Main) {
                        if (responseData != null) {
                            onSuccess.invoke(responseData)
                        }
                    }
                }
            }
            override fun onFailure(call: Call<GenericResponse<CategoryBooks>>?, t: Throwable?) {
                onFailure.invoke(t?.message)
            }

        })
    }
    fun getSubcategory(
        onSuccess: (response: List<String>) -> Unit,
        onFailure: (msg: String?) -> Unit
    ) {
        val call = apiClient.create(ApiInterface::class.java).getSubcategory()
        call.enqueue(object : Callback<GenericResponse<List<List<Subcategory>>>> {
            override fun onResponse(
                call: Call<GenericResponse<List<List<Subcategory>>>>,
                response: Response<GenericResponse<List<List<Subcategory>>>>
            ) {
                val responseData = arrayListOf<String>()
                CoroutineScope(Dispatchers.IO).launch {
                    response.body()?.data?.forEach {
                        it.forEach {
                            if (it.category_id ==5){
                                responseData.add(it.name)
                            }
                        }
                    }
                    Log.d("lists",response.body().toString())
                    withContext(Dispatchers.Main) {
                        if (responseData != null) {
                            onSuccess.invoke(responseData)
                        }
                    }
                }
            }

            override fun onFailure(call: Call<GenericResponse<List<List<Subcategory>>>>?, t: Throwable?) {
                onFailure.invoke(t?.message)
                Log.d("lists",t.toString())
            }

        })
    }

    fun getBookDetails(
        bookId:Int,
        onSuccess: (response: BookDetailsResponse) -> Unit,
        onFailure: (msg: String?) -> Unit
    ) {
        val call = apiClient.create(ApiInterface::class.java).getAudios(bookId)
        call.enqueue(object : Callback<GenericResponse<BookDetailsResponse>> {
            override fun onResponse(
                call: Call<GenericResponse<BookDetailsResponse>>,
                response: Response<GenericResponse<BookDetailsResponse>>
            ) {

                CoroutineScope(Dispatchers.IO).launch {
                    val responseData = response.body()?.data
                    withContext(Dispatchers.Main) {
                        if (responseData != null) {
                            onSuccess.invoke(responseData)
                        }
                    }
                }
            }

            override fun onFailure(call: Call<GenericResponse<BookDetailsResponse>>?, t: Throwable?) {
                onFailure.invoke(t?.message)
                Log.d("lists",t.toString())
            }

        })
    }
}