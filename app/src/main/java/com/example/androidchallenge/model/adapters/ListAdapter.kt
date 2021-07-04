package com.example.androidchallenge.model.adapters

import android.graphics.Color
import android.graphics.Paint
import android.graphics.drawable.ShapeDrawable
import android.graphics.drawable.shapes.OvalShape
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.androidchallenge.OnItemClickListener
import com.example.androidchallenge.databinding.ListItemBinding
import com.example.androidchallenge.model.pojo.BioMarker
import com.example.androidchallenge.util.ColorGenerator

class ListAdapter(dataset: ArrayList<BioMarker>, listener: OnItemClickListener): RecyclerView.Adapter<ListAdapter.ViewHolder>() {
    private var mDataSet: MutableList<BioMarker> = dataset
    private var clickListener: OnItemClickListener = listener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    fun addBiomarker(bioMarker: BioMarker){
        mDataSet.add(bioMarker)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder){
            with(mDataSet[position]){
                binding.textView.text = this.date + "\n" + this.value
                val colorGenerator = ColorGenerator(this.color)
                val shapeDrawable = ShapeDrawable()
                shapeDrawable.shape = OvalShape()
                shapeDrawable.paint.color = Color.parseColor(this.color)
                shapeDrawable.paint.strokeWidth = 23f
                shapeDrawable.paint.style = Paint.Style.STROKE
                binding.rlvSymbolView.background = shapeDrawable
                binding.rlvSymbolView.backgroundColor = colorGenerator.lightColor
                binding.rlvSymbolView.titleText = this.symbol
                binding.rlvSymbolView.setTitleColor(Color.parseColor(this.color))
                binding.root.setOnClickListener {
                    clickListener.onItemClick(position)
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return mDataSet.size
    }

    //create an inner class with name ViewHolder
    inner class ViewHolder(val binding: ListItemBinding): RecyclerView.ViewHolder(binding.root) {
    }
}