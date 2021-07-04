package com.example.androidchallenge

import android.graphics.Color
import android.graphics.Paint
import android.graphics.drawable.ShapeDrawable
import android.graphics.drawable.shapes.OvalShape
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.androidchallenge.databinding.ActivityDetailsBinding
import com.example.androidchallenge.model.pojo.BioMarker
import com.example.androidchallenge.util.ColorGenerator

class DetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        val biomarker: BioMarker = intent.getSerializableExtra("biomarker") as BioMarker
        binding.tvCategory.text = biomarker.category
        binding.tvSymbol.text = biomarker.symbol
        val colorGenerator = ColorGenerator(biomarker.color)
        val shapeDrawable = ShapeDrawable()
        shapeDrawable.shape = OvalShape()
        shapeDrawable.paint.color = Color.parseColor(biomarker.color)
        shapeDrawable.paint.strokeWidth = 23f
        shapeDrawable.paint.style = Paint.Style.STROKE
        binding.rlvSymbol.background = shapeDrawable
        binding.rlvSymbol.backgroundColor = colorGenerator.lightColor
        binding.rlvSymbol.titleText = biomarker.symbol
        binding.rlvSymbol.setTitleColor(Color.parseColor(biomarker.color))
        binding.tvDate.text = biomarker.date
        binding.tvResult.text = "Your result is " + biomarker.value
        binding.tvInfo.text = biomarker.info
        binding.tvInsight.text = biomarker.insight
        binding.ivInfo.setOnClickListener {
            showPopupMessage(biomarker.info)
        }
    }

    fun showPopupMessage(info: String){
        val dialog = AlertDialog.Builder(this)
                .setMessage(info).create()
        dialog.show()
    }
}