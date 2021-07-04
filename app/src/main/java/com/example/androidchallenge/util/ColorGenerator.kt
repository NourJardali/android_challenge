package com.example.androidchallenge.util

import android.graphics.Color


class ColorGenerator(color: String) {
    var lightColor: Int
    private val lightArray = arrayOf("#FADAD1", "#d4faff")
    private val darkColor = arrayOf("#E64A19", "#00BCD4")

    init {
        if(color == darkColor[0]){
            lightColor = Color.parseColor(lightArray[0])
        }
        else{
            lightColor = Color.parseColor(lightArray[1])
        }
    }
}