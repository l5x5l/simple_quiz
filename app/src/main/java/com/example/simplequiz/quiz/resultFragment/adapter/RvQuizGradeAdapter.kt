package com.example.simplequiz.quiz.resultFragment.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.simplequiz.R
import com.example.simplequiz.databinding.ItemQuizGradeBinding

class RvQuizGradeAdapter(private val context: Context, private val dataList : ArrayList<Boolean>) : RecyclerView.Adapter<RvQuizGradeAdapter.ViewHolder>() {

    private val inflate = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    private lateinit var binding : ItemQuizGradeBinding

    class ViewHolder(binding: ItemQuizGradeBinding) : RecyclerView.ViewHolder(binding.root){
        val quizNum = binding.tvQuizNumber
        val tvResult = binding.tvResult
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = ItemQuizGradeBinding.inflate(inflate, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.quizNum.text = (position + 1).toString()
        if (dataList[position]) {
            holder.tvResult.text = "O"
            holder.tvResult.setTextColor(ContextCompat.getColor(context, R.color.starbucks_green))
        } else {
            holder.tvResult.text = "X"
            holder.tvResult.setTextColor(ContextCompat.getColor(context, R.color.orange))
        }
    }

    override fun getItemCount(): Int = dataList.size
}