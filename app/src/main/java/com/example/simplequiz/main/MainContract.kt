package com.example.simplequiz.main

import android.content.Intent
import com.example.simplequiz.config.BasePresenter

interface MainContract {
    interface View {
        fun applyData(ready : ArrayList<Boolean>, solved : ArrayList<Boolean>)
        fun goToQuiz(quizNum : Int)
        fun goToHome()
    }

    interface Presenter : BasePresenter<View>{
        fun showQuizState(intent: Intent?)
        fun checkQuiz(quizNum : Int)
    }
}