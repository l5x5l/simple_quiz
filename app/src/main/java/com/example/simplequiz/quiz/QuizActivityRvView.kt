package com.example.simplequiz.quiz

interface QuizActivityRvView {
    fun isFirst(idx : Int) : Boolean

    fun isLast(idx : Int) : Boolean

    fun goToPrev(idx : Int)

    fun goToNext(idx : Int)
}