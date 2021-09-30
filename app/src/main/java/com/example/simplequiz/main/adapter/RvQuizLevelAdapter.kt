package com.example.simplequiz.main.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.simplequiz.R
import com.example.simplequiz.databinding.ItemQuizLevelBinding
import com.example.simplequiz.main.MainActivity
import com.example.simplequiz.main.MainContract

class RvQuizLevelAdapter(private val context : Context) : RecyclerView.Adapter<RvQuizLevelAdapter.ViewHolder>() {

    private lateinit var binding : ItemQuizLevelBinding
    private val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    // state 설명, 0 -> 준비 안된 문제, 1 -> 풀지 못한 문제, 2 -> 맞춘 문제
    private val dataList = arrayListOf(0, 0, 0)

    class ViewHolder(binding : ItemQuizLevelBinding) : RecyclerView.ViewHolder(binding.root){
        val mainLayout = binding.itemQuizLevelMain
        val levelText = binding.ivQuizLevel
        val text = binding.tvQuizLevel
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = ItemQuizLevelBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (position < dataList.size){
            holder.levelText.text = (position + 1).toString()
            holder.mainLayout.setOnClickListener {
                (context as MainActivity).presenter.checkQuiz((position + 1))
            }

            when(dataList[position]) {
                0 -> {
                    holder.levelText.setTextColor(ContextCompat.getColor(context, R.color.gray))
                    holder.text.setTextColor(ContextCompat.getColor(context, R.color.gray))
                    holder.mainLayout.setBackgroundResource(R.drawable.shape_radius_8_stroke_gray)
                }
                1 -> {
                    holder.levelText.setTextColor(ContextCompat.getColor(context, R.color.black))
                    holder.text.setTextColor(ContextCompat.getColor(context, R.color.black))
                    holder.mainLayout.setBackgroundResource(R.drawable.shape_radius_8_stroke_black)
                }
                else -> {
                    holder.levelText.setTextColor(ContextCompat.getColor(context, R.color.starbucks_green))
                    holder.text.setTextColor(ContextCompat.getColor(context, R.color.starbucks_green))
                    holder.mainLayout.setBackgroundResource(R.drawable.shape_radius_8_stroke_green)
                }
            }

        } else {
            holder.levelText.text = "X"
            holder.text.visibility = View.GONE
            holder.mainLayout.setOnClickListener{
                (context as MainActivity).goToHome()
            }
        }
    }

    override fun getItemCount(): Int = dataList.size + 1

    fun applyData(isReady : ArrayList<Boolean>, isSolve : ArrayList<Boolean>){
        for (idx in 0 until isReady.size) {
            if (isReady[idx] && isSolve[idx]){
                dataList[idx] = 2
            } else if (isReady[idx] && !isSolve[idx]) {
                dataList[idx] = 1
            } else {
                dataList[idx] = 0
            }
        }
        Log.d("list", dataList.toString())
        notifyDataSetChanged()
    }
}