package com.example.simplequiz.quiz.resultFragment

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.simplequiz.R
import com.example.simplequiz.config.BaseFragment
import com.example.simplequiz.databinding.FragmentResultBinding
import com.example.simplequiz.quiz.QuizActivity
import com.example.simplequiz.quiz.resultFragment.adapter.RvQuizGradeAdapter
import com.example.simplequiz.quiz.resultFragment.decoration.RvQuizGradeDecoration

class ResultFragment(private val gradeList : ArrayList<Boolean>) : BaseFragment<FragmentResultBinding>(FragmentResultBinding::bind, R.layout.fragment_result){
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setButton()
        setRecyclerView()
    }

    private fun setRecyclerView() {
        binding.rvGrade.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        binding.rvGrade.adapter = RvQuizGradeAdapter(activity as QuizActivity, gradeList)
        binding.rvGrade.addItemDecoration(RvQuizGradeDecoration(activity as QuizActivity))
    }

    private fun setButton() {
        binding.btnBack.setOnClickListener {
            (activity as QuizActivity).backWithResult()
        }
    }
}