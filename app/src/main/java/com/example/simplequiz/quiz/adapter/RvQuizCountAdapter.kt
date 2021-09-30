package com.example.simplequiz.quiz.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.simplequiz.R
import com.example.simplequiz.databinding.ItemQuizCountBinding

class RvQuizCountAdapter(private val context : Context, quizCount : Int ?= null) : RecyclerView.Adapter<RvQuizCountAdapter.ViewHolder>() {

    private lateinit var binding : ItemQuizCountBinding
    private val inflate = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    private var quizCount = quizCount ?: 0
    private var quizPosition = 0

    class ViewHolder(binding : ItemQuizCountBinding) : RecyclerView.ViewHolder(binding.root) {
        val mainLayout = binding.itemQuizCountMain
        val countText = binding.tvCount
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = ItemQuizCountBinding.inflate(inflate, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.countText.text = (position + 1).toString()
        if (position == quizPosition){
            holder.countText.setTextColor(ContextCompat.getColor(context, R.color.white))
            holder.mainLayout.setBackgroundResource(R.drawable.shape_radius_max_solid_black)
        } else {
            holder.countText.setTextColor(ContextCompat.getColor(context, R.color.black))
            holder.mainLayout.setBackgroundResource(R.drawable.shape_radius_max_stroke_black)
        }
    }

    override fun getItemCount(): Int = quizCount

    fun changeTo(idx : Int){
        quizPosition = idx
        notifyDataSetChanged()
    }

    fun changeCount(count : Int){
        quizCount = count
    }
}