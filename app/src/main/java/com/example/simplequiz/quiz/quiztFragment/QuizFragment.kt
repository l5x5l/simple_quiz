package com.example.simplequiz.quiz.quiztFragment

import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.simplequiz.R
import com.example.simplequiz.config.BaseFragment
import com.example.simplequiz.databinding.FragmentQuizBinding
import com.example.simplequiz.quiz.QuizActivity
import com.example.simplequiz.quiz.QuizActivityRvView
import com.example.simplequiz.quiz.quiztFragment.adapter.RvQuizAnswerAdapter
import com.example.simplequiz.quiz.data.Quiz

class QuizFragment(private val quiz : Quiz, private val view : QuizActivityRvView) : BaseFragment<FragmentQuizBinding>(FragmentQuizBinding::bind, R.layout.fragment_quiz), QuizFragmentRvView{
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.tvQuizNumber.text = getString(R.string.quiz_num, quiz.questionIdx)
        when(quiz.questionType){
            1 -> binding.tvQuiz.text = getString(R.string.str_select_output)
            2 -> binding.tvQuiz.text = getString(R.string.str_select_error_point)
        }

        if (quiz.content != null){
            binding.tvContent.visibility = View.VISIBLE
            binding.tvContent.text = quiz.content
        }

        setRecyclerView()
        setButton()
    }

    private fun setButton() {

        binding.btnPrev.setOnClickListener {
            view.goToPrev(quiz.questionIdx)
        }
        binding.btnNext.setOnClickListener {
            view.goToNext(quiz.questionIdx)
        }

        binding.btnPrev.isClickable = !view.isFirst(quiz.questionIdx)
        //binding.btnNext.isClickable = !view.isLast(quiz.questionIdx)

        when (binding.btnPrev.isClickable) {
            true -> binding.btnPrev.setColorFilter(ContextCompat.getColor(activity as QuizActivity, R.color.black))
            false -> binding.btnPrev.setColorFilter(ContextCompat.getColor(activity as QuizActivity, R.color.white))
        }
        when (binding.btnNext.isClickable) {
            true -> binding.btnNext.setColorFilter(ContextCompat.getColor(activity as QuizActivity, R.color.black))
            false -> binding.btnNext.setColorFilter(ContextCompat.getColor(activity as QuizActivity, R.color.white))
        }

    }

    private fun setRecyclerView(){
        binding.rvAnswer.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        binding.rvAnswer.adapter = RvQuizAnswerAdapter(activity as QuizActivity, quiz.answers, this)
    }

    override fun gradeQuiz(answer: Int) {
        (activity as QuizActivity).presenter.gradeQuiz(quiz.questionIdx, answer)
    }
}