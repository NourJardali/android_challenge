package com.example.androidchallenge.model.pojo

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

class BioMarker(id: Int, date: String, info: String, color: String,
                value: String, symbol: String, insight: String,
                category: String): Serializable {
    @SerializedName("id")
    @Expose
    var id: Int = id
    @SerializedName("date")
    @Expose
    var date: String = date
    @SerializedName("info")
    @Expose
    var info: String = info
    @SerializedName("color")
    @Expose
    var color: String = color
    @SerializedName("value")
    @Expose
    var value: String = value
    @SerializedName("symbol")
    @Expose
    var symbol: String = symbol
    @SerializedName("insight")
    @Expose
    var insight: String = insight
    @SerializedName("category")
    @Expose
    var category: String = category
}