package com.example.simplequiz.main

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.GridLayoutManager
import com.example.simplequiz.config.BaseActivity
import com.example.simplequiz.databinding.ActivityMainBinding
import com.example.simplequiz.main.adapter.RvQuizLevelAdapter
import com.example.simplequiz.main.decoration.RvQuizLevelDecoration
import com.example.simplequiz.quiz.QuizActivity

class MainActivity : BaseActivity<ActivityMainBinding>(ActivityMainBinding::inflate),MainContract.View{

    lateinit var activityResultLauncher : ActivityResultLauncher<Intent>
    val presenter = MainPresenter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityResultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            result : ActivityResult ->
            if (result.resultCode == Activity.RESULT_OK){
                val intent = result.data!!
                presenter.showQuizState(intent)
            }
        }

        setRecyclerView()
        presenter.takeView(this)
        presenter.showQuizState(null)
    }

    override fun onDestroy() {
        presenter.dropView()
        super.onDestroy()
    }

    override fun applyData(ready : ArrayList<Boolean>, solved : ArrayList<Boolean>) {
        (binding.rv.adapter as RvQuizLevelAdapter).applyData(ready, solved)
    }

    private fun setRecyclerView(){
        binding.rv.layoutManager = GridLayoutManager(this, 2)
        binding.rv.adapter = RvQuizLevelAdapter(this)
        binding.rv.addItemDecoration(RvQuizLevelDecoration(this))
    }

    override fun goToQuiz(quizNum: Int) {
        val intent = Intent(this, QuizActivity::class.java)
        activityResultLauncher.launch(intent)
    }

    override fun goToHome() {
        onBackPressed()
    }
}