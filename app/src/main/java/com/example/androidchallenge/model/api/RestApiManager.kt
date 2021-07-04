package com.example.androidchallenge.model.api

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RestApiManager {
    var biomarkerApi: GetBiomarkersAPI? = null
    val BASE_URL = "https://retoolapi.dev"

    fun getBiomarkersApi(): GetBiomarkersAPI? {
        if(biomarkerApi == null){
           val okHttpClient = OkHttpClient.Builder().build()
            biomarkerApi = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build().create(GetBiomarkersAPI::class.java)
        }
        return biomarkerApi
    }
}