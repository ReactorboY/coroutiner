package com.organicpy.coroutinedemo.repository

import com.organicpy.coroutinedemo.api.ApiService
import javax.inject.Inject

class MainRepository @Inject constructor(private val retrofitService: ApiService) {
    suspend fun getGenderFromRepo(name : String) = retrofitService.getGender(name)
}