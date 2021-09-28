package com.example.simplequiz.quiz

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.simplequiz.config.BaseActivity
import com.example.simplequiz.databinding.ActivityQuizBinding
import com.example.simplequiz.main.MainActivity
import com.example.simplequiz.quiz.adapter.RvQuizCountAdapter
import com.example.simplequiz.quiz.quiztFragment.QuizFragment
import com.example.simplequiz.quiz.decoration.RvQuizCountDecoration
import com.example.simplequiz.quiz.resultFragment.ResultFragment

class QuizActivity : BaseActivity<ActivityQuizBinding>(ActivityQuizBinding::inflate), QuizActivityRvView{

    val model = QuizActivityModel()
    private val fragments = arrayListOf<Fragment>(QuizFragment(model.getQuiz(0), this), QuizFragment(model.getQuiz(1), this))
    private var previousFragment = fragments[0]

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setFragment()
        setRecyclerView()
    }

    private fun setRecyclerView() {
        binding.rvQuizCount.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        binding.rvQuizCount.adapter = RvQuizCountAdapter(this, model.getQuizCount())
        binding.rvQuizCount.addItemDecoration(RvQuizCountDecoration(this))
    }

    private fun setFragment() {

        for (i in (fragments.size - 1) downTo 1){
            supportFragmentManager.beginTransaction().add(binding.layoutFragment.id, fragments[i]).commit()
            supportFragmentManager.beginTransaction().hide(fragments[i]).commit()
        }

        supportFragmentManager.beginTransaction().add(binding.layoutFragment.id, fragments[0]).commit()
    }

    override fun isFirst(idx: Int): Boolean {
        return (idx == 0)
    }

    override fun isLast(idx: Int): Boolean {
        return (idx == model.getQuizCount() - 1)
    }

    override fun goToPrev(idx: Int) {
        if (idx > 0) {
            supportFragmentManager.beginTransaction().hide(previousFragment).commit()
            supportFragmentManager.beginTransaction().show(fragments[idx - 1]).commit()
            previousFragment = fragments[idx - 1]

            (binding.rvQuizCount.adapter as RvQuizCountAdapter).changeTo(idx - 1)
        }
    }

    override fun goToNext(idx: Int) {
        if (idx < model.getQuizCount() - 1){
            supportFragmentManager.beginTransaction().hide(previousFragment).commit()
            supportFragmentManager.beginTransaction().show(fragments[idx + 1]).commit()
            previousFragment = fragments[idx + 1]

            (binding.rvQuizCount.adapter as RvQuizCountAdapter).changeTo(idx + 1)
        } else if (idx == model.getQuizCount() - 1) {
            supportFragmentManager.beginTransaction().hide(previousFragment).commit()
            supportFragmentManager.beginTransaction().add(binding.layoutFragment.id, ResultFragment(model.getGrades())).commit()
            binding.rvQuizCount.visibility = View.GONE
        }
    }

    fun backWithResult(){
        val intent = Intent(this, MainActivity::class.java).apply {
            putExtra("quizNum", 0)
            putExtra("result", model.getAllGrades())
        }
        setResult(RESULT_OK, intent)
        onBackPressed()
    }
}