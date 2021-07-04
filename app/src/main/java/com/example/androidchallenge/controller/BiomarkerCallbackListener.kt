package com.example.androidchallenge.controller

import com.example.androidchallenge.model.pojo.BioMarker

interface BiomarkerCallbackListener {
    fun onFetchProgress(list: List<BioMarker>?)

    fun onFetchComplete(success: Boolean)
}