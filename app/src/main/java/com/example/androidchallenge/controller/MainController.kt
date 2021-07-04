package com.example.androidchallenge.controller

import android.util.Log
import com.example.androidchallenge.model.api.RestApiManager
import com.example.androidchallenge.model.pojo.BioMarker
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainController(listener: BiomarkerCallbackListener): IMainController {
    var restApiManager: RestApiManager = RestApiManager()
    val TAG = MainController::class.java.simpleName
    var biomarkerListener: BiomarkerCallbackListener = listener

    override fun startFetching() {
        val biomarkersCall = restApiManager.getBiomarkersApi()?.getBioMarkers()
        biomarkersCall?.enqueue(object: Callback<List<BioMarker>> {
            override fun onResponse(
                call: Call<List<BioMarker>>,
                response: Response<List<BioMarker>>
            ) {
                if(response.code() == 200){
                    biomarkerListener.onFetchProgress(response.body())
                    biomarkerListener.onFetchComplete(true)
                }
            }

            override fun onFailure(call: Call<List<BioMarker>>, t: Throwable) {
                Log.d(TAG, "Error: " + t.message)
                biomarkerListener.onFetchComplete(false)
            }

        })
    }
}