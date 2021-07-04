package com.example.androidchallenge.model.api

import com.example.androidchallenge.model.pojo.BioMarker
import retrofit2.Call
import retrofit2.http.GET

interface GetBiomarkersAPI {
    @GET("/hZZ5j8/biomarkers")
    fun getBioMarkers(): Call<List<BioMarker>>
}