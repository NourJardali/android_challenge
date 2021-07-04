package com.example.androidchallenge

import android.app.ActivityOptions
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.transition.Slide
import android.view.Gravity
import android.view.animation.DecelerateInterpolator
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.androidchallenge.controller.BiomarkerCallbackListener
import com.example.androidchallenge.controller.MainController
import com.example.androidchallenge.databinding.ActivityMainBinding
import com.example.androidchallenge.model.adapters.ListAdapter
import com.example.androidchallenge.model.pojo.BioMarker
import com.faltenreich.skeletonlayout.Skeleton
import com.faltenreich.skeletonlayout.applySkeleton


class MainActivity : AppCompatActivity(), BiomarkerCallbackListener, OnItemClickListener {
    var biomarkersList = ArrayList<BioMarker>()
    private lateinit var binding: ActivityMainBinding
    lateinit var biomarkerAdapter: ListAdapter
    lateinit var controller: MainController
    private lateinit var skeleton: Skeleton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setAnimation()
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        controller = MainController(this)
        configViews()
        skeleton.showSkeleton()
        val handler = Handler()
        handler.postDelayed({
            controller.startFetching()
        }, 1000)
    }

    private fun configViews(){
        binding.biomarkerList.setHasFixedSize(true)
        binding.biomarkerList.layoutManager = LinearLayoutManager(this)
        binding.biomarkerList.setRecycledViewPool(RecyclerView.RecycledViewPool())

        biomarkerAdapter = ListAdapter(biomarkersList, this)
        binding.biomarkerList.adapter = biomarkerAdapter

        skeleton = binding.biomarkerList.applySkeleton(R.layout.list_item)
    }

    override fun onFetchProgress(list: List<BioMarker>?) {
        this.biomarkersList = list as ArrayList<BioMarker>
        for(marker in biomarkersList){
            if(marker.color != null && marker.value != null && marker.date != null && marker.category != null && marker.info != null && marker.insight != null && marker.symbol != null){
                biomarkerAdapter.addBiomarker(marker)
            }
        }
        biomarkerAdapter.notifyDataSetChanged()
    }

    override fun onFetchComplete(success: Boolean) {
        if(success){
            skeleton.showOriginal()
            Toast.makeText(this, "Fetch process is complete", Toast.LENGTH_LONG).show()
        }
        else{
            val dialog = AlertDialog.Builder(this)
                .setMessage("Connection Failed! Try again later").create()
            dialog.show()
        }
    }

    override fun onItemClick(position: Int) {
        startActivity(position)
    }

    fun setAnimation(){
        if(Build.VERSION.SDK_INT > 20){
            val slide = Slide()
            slide.slideEdge = Gravity.LEFT
            slide.duration = 400
            slide.interpolator = DecelerateInterpolator()
            window.exitTransition = slide
            window.enterTransition = slide
        }
    }

    fun startActivity(position: Int){
        val detailsIntent = Intent(this, DetailsActivity::class.java)

        if(Build.VERSION.SDK_INT > 20){
            val options = ActivityOptions.makeSceneTransitionAnimation(this)
            detailsIntent.putExtra("biomarker", biomarkersList[position])
            startActivity(detailsIntent, options.toBundle())
        }
        else{
            detailsIntent.putExtra("biomarker", biomarkersList[position])
            startActivity(detailsIntent)
        }
    }
}