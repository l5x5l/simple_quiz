package com.example.simplequiz.main

import android.content.Intent

class MainPresenter : MainContract.Presenter {

    private val model = MainActivityModel()
    private var mainView : MainContract.View ?= null

    override fun showQuizState(intent: Intent?) {
        if (intent != null){
            val tempSolved = intent.getBooleanExtra("result", false)
            val tempQuizIdx = intent.getIntExtra("quizNum", 0)
            if (tempSolved) model.solve(tempQuizIdx + 1)
        }
        mainView!!.applyData(model.getReady(), model.getSolved())
    }

    override fun checkQuiz(quizNum: Int) {
        if (model.isAvailable(quizNum)) {
            mainView!!.goToQuiz(quizNum)
        }
    }

    override fun takeView(view: MainContract.View) {
        mainView = view
    }

    override fun dropView() {
        mainView = null
    }
}