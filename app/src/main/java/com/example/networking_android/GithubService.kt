package com.example.networking_android

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GithubService {
    @GET("users")
    suspend fun getUsers():Response<List<User>>

    @GET("users/{id}")
    suspend fun getUserbyId(@Path("id")id:String?):Response<User>

    @GET("search/users")
    suspend fun searchUsers(@Query("q")q:String?):Response<List<User>>
}