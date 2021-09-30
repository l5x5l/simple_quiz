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
import com.example.simplequiz.quiz.data.Quiz
import com.example.simplequiz.quiz.quiztFragment.QuizFragment
import com.example.simplequiz.quiz.decoration.RvQuizCountDecoration
import com.example.simplequiz.quiz.resultFragment.ResultFragment

class QuizActivity : BaseActivity<ActivityQuizBinding>(ActivityQuizBinding::inflate), QuizActivityRvView, QuizContract.View{

    val presenter = QuizPresenter()
    private val fragments = arrayListOf<Fragment>()
    private lateinit var previousFragment : Fragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        presenter.takeView(this)
        presenter.setQuizzes()
        setFragment()

    }

    override fun onDestroy() {
        presenter.dropView()
        super.onDestroy()
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
        return (idx == presenter.getQuizCount() - 1)    // stat 객체가 필요하구나!
    }

    override fun goToPrev(idx: Int) {
        if (idx > 0) {
            supportFragmentManager.beginTransaction().hide(previousFragment).commit()
            supportFragmentManager.beginTransaction().show(fragments[idx - 1]).commit()
            previousFragment = fragments[idx - 1]

            (binding.rvQuizCount.adapter as RvQuizCountAdapter).changeTo(idx - 1)
        }
    }

    override fun goToNext(idx: Int) = presenter.goToNext(idx)/*{
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
    }*/

    fun backWithResult(){
        val intent = Intent(this, MainActivity::class.java).apply {
            putExtra("quizNum", 0)
            putExtra("result", presenter.getAllGrades())
        }
        setResult(RESULT_OK, intent)
        onBackPressed()
    }

    override fun setQuizFragments(quizList: ArrayList<Quiz>) {
        for (quiz in quizList){
            fragments.add(QuizFragment(quiz, this))
        }
        previousFragment = fragments[0]
    }

    override fun setQuizCountView(quizCount: Int) {
        binding.rvQuizCount.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        binding.rvQuizCount.adapter = RvQuizCountAdapter(this, quizCount)
        binding.rvQuizCount.addItemDecoration(RvQuizCountDecoration(this))
    }

    override fun goToNextQuiz(idx: Int) {
        supportFragmentManager.beginTransaction().hide(previousFragment).commit()
        supportFragmentManager.beginTransaction().show(fragments[idx + 1]).commit()
        previousFragment = fragments[idx + 1]
    }

    override fun goToResult(grades : ArrayList<Boolean>) {
        supportFragmentManager.beginTransaction().hide(previousFragment).commit()
        supportFragmentManager.beginTransaction().add(binding.layoutFragment.id, ResultFragment(grades)).commit()
        binding.rvQuizCount.visibility = View.GONE
    }
}