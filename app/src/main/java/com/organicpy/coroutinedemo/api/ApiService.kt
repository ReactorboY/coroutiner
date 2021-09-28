package com.organicpy.coroutinedemo.api

import com.organicpy.coroutinedemo.models.Genderise
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("/")
    suspend fun getGender(@Query("name") name : String): Response<Genderise>
}