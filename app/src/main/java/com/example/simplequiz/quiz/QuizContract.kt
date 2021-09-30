package com.example.simplequiz.quiz

import com.example.simplequiz.config.BasePresenter
import com.example.simplequiz.quiz.data.Quiz

interface QuizContract {
    interface View {
        fun setQuizFragments(quizList : ArrayList<Quiz>)
        fun setQuizCountView(quizCount : Int)
        fun goToNextQuiz(idx : Int)
        fun goToResult(grades : ArrayList<Boolean>)
    }

    interface Presenter : BasePresenter<View>{
        fun setQuizzes() // 퀴즈에 대한 모든 정보 흭득 후 수정
        fun getQuizCount() : Int // 원래 이러면 안되는데, 상태를 가지고 있는 객체를 별도로 안만들어서 임시로 사용
        fun getAllGrades() : Boolean
        fun gradeQuiz(userAnswer : Int, answer : Int)
        fun goToNext(idx : Int)
    }
}