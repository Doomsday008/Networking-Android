package com.example.networking_android

import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Client {
    private val gson = GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create()
    private val retrofit = Retrofit.Builder()
            .baseUrl("https://api.github.com/users/")
            .addConverterFactory(GsonConverterFactory.create(gson)).build()
    val api: GithubService = retrofit.create(GithubService::class.java)

}