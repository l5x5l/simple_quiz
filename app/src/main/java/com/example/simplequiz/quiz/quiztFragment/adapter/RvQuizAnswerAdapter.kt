package com.example.simplequiz.quiz.quiztFragment.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.simplequiz.databinding.ItemQuizAnswerBinding
import com.example.simplequiz.quiz.quiztFragment.QuizFragmentRvView

class RvQuizAnswerAdapter(private val context : Context, private val answerList : ArrayList<String>, private val view : QuizFragmentRvView) : RecyclerView.Adapter<RvQuizAnswerAdapter.ViewHolder>(){

    private val inflate = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    private lateinit var binding : ItemQuizAnswerBinding
    private var checkIdx = -1

    class ViewHolder(binding : ItemQuizAnswerBinding) : RecyclerView.ViewHolder(binding.root) {
        val mainLayout = binding.itemQuizAnswerMain
        val answer = binding.tv
        val checked = binding.rb
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = ItemQuizAnswerBinding.inflate(inflate, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.mainLayout.setOnClickListener {
            checkIdx(position)
        }
        holder.answer.text = answerList[position]
        holder.checked.isChecked = (position == checkIdx)
    }

    override fun getItemCount(): Int = answerList.size

    private fun checkIdx(idx : Int){
        checkIdx = idx
        view.gradeQuiz(checkIdx)
        notifyDataSetChanged()
    }
}