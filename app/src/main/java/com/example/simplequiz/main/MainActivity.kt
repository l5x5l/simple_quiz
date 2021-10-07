package com.example.simplequiz.main

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.simplequiz.config.BaseActivity
import com.example.simplequiz.databinding.ActivityMainBinding
import com.example.simplequiz.main.adapter.RvQuizLevelAdapter
import com.example.simplequiz.main.decoration.RvQuizLevelDecoration
import com.example.simplequiz.quiz.QuizActivity

class MainActivity : BaseActivity<ActivityMainBinding>(ActivityMainBinding::inflate), MainActivityRvView{

    lateinit var activityResultLauncher : ActivityResultLauncher<Intent>
    lateinit var viewModel : MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setRecyclerView()

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        val statDataObserver : Observer<ArrayList<Int>> = Observer { liveData ->
            (binding.rv.adapter as RvQuizLevelAdapter).applyData2(liveData)
        }
        viewModel.stateList.observe(this, statDataObserver)

        activityResultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            result : ActivityResult ->
            if (result.resultCode == Activity.RESULT_OK){
                val intent = result.data!!
                val tempSolved = intent.getBooleanExtra("result", false)
                val tempQuizIdx = intent.getIntExtra("quizNum", 0)
                if (tempSolved) viewModel.solved(tempQuizIdx + 1)
                //applyData()
            }
        }

        binding.btnRefresh.setOnClickListener {
            viewModel.clearState()
        }

        viewModel.initState()
    }

    private fun applyData() {
        (binding.rv.adapter as RvQuizLevelAdapter).applyData2(viewModel.stateList.value!!)
    }

    private fun setRecyclerView(){
        binding.rv.layoutManager = GridLayoutManager(this, 2)
        binding.rv.adapter = RvQuizLevelAdapter(this, this)
        binding.rv.addItemDecoration(RvQuizLevelDecoration(this))
    }

    override fun goToQuiz(quizNum: Int) {
        if (viewModel.isAvailable(quizNum)){
            val intent = Intent(this, QuizActivity::class.java)
            activityResultLauncher.launch(intent)
        }
    }

    override fun goToHome() {
        onBackPressed()
    }
}