package com.bizmiz.bookieuz.ui.auth.api

import com.bizmiz.bookieuz.ui.model.*
import retrofit2.Call
import retrofit2.http.*

interface ApiInterface {
    @Headers("Content-Type:application/json")
    @POST("api/register")
    fun signUp(
        @Body postModel: SignUpData
    ): Call<GenericResponse<Data>>

    @Headers("Content-Type:application/json")
    @POST("api/login")
    fun signIn(
        @Body postModel: SignInData
    ): Call<GenericResponse<Data>>

    @GET("api/category")
    fun getCategory(
    ): Call<GenericResponse<List<DataX>>>

    @GET("api/subcategory")
    fun getSubcategory(
    ): Call<GenericResponse<List<List<Subcategory>>>>

    @GET("api/book/")
    fun getLatest(
    ): Call<GenericResponse<LatestData>>

    @GET("api/book/show/{categoryId}?")
    fun getCategoryDataByPage(
        @Path("categoryId") categoryId: Int,
        @Query("page") page: Int,
    ): Call<GenericResponse<CategoryBooks>>

    @GET("api/audio/{bookId}")
    fun getAudios(
        @Path("bookId") bookId: Int
    ): Call<GenericResponse<BookDetailsResponse>>
}