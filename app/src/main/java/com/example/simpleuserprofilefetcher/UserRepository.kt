package com.example.simpleuserprofilefetcher

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class UserRepository {

    private val api: UserApiService

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        api = retrofit.create(UserApiService::class.java)
    }

    fun getUserProfile() = api.getUserProfile()
}