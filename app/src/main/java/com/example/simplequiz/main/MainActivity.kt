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

class MainActivity : BaseActivity<ActivityMainBinding>(ActivityMainBinding::inflate), MainActivityRvView{
    private val model = MainActivityModel()
    lateinit var activityResultLauncher : ActivityResultLauncher<Intent>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityResultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            result : ActivityResult ->
            if (result.resultCode == Activity.RESULT_OK){
                val intent = result.data!!
                val tempSolved = intent.getBooleanExtra("result", false)
                val tempQuizIdx = intent.getIntExtra("quizNum", 0)
                if (tempSolved) model.solve(tempQuizIdx + 1)
                applyData()
            }
        }

        setRecyclerView()
        applyData()
    }

    private fun applyData() {
        (binding.rv.adapter as RvQuizLevelAdapter).applyData(model.getReady(), model.getSolved())
    }

    private fun setRecyclerView(){
        binding.rv.layoutManager = GridLayoutManager(this, 2)
        binding.rv.adapter = RvQuizLevelAdapter(this, this)
        binding.rv.addItemDecoration(RvQuizLevelDecoration(this))
    }

    override fun goToQuiz(quizNum: Int) {
        if (model.isAvailable(quizNum)){
            val intent = Intent(this, QuizActivity::class.java)
            activityResultLauncher.launch(intent)
        }
    }

    override fun goToHome() {
        onBackPressed()
    }
}