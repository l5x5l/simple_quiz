package com.example.simplequiz.quiz.data

data class Quiz (
        val questionIdx : Int,
        val questionType : Int,
        val answerIdx : Int,
        val answers : ArrayList<String>,
        val content : String? = null
)