package com.example.simplequiz.quiz

import com.example.simplequiz.quiz.data.Quiz

class QuizActivityRepository {

    private val quiz1 = Quiz(0, 1, 1, arrayListOf("10", "45", "55", "런타임 에러", "컴파일 에러"), "sum=0\nfor i in range(0, 10) :\n\tsum+=1\nprint(sum)")
    private val quiz2 = Quiz(1, 2, 5,
            arrayListOf("for k in range(0, len(list)) :", "\tfor i in range(0, len(list)) :", "\t\tfor j in range(0, len(list)) :",
                    "\t\t\tif list[i][j] > list[i][k] + list[k][j] :", "\t\t\t\tlist[i][j] = list[i][k] + list[k][j]", "발생하지 않는다"))

    private val quizList = arrayListOf(quiz1, quiz2)
    private val gradeList = ArrayList<Boolean>()

    init {
        for (i in 0 until quizList.size) {
            gradeList.add(false)
        }
    }

    fun getQuizAll() : ArrayList<Quiz>{
        return quizList
    }

    fun getQuiz(quizIdx : Int) : Quiz{
        return quizList[quizIdx]
    }

    fun gradeQuiz(quizIdx : Int, answer : Int){
        gradeList[quizIdx] = (quizList[quizIdx].answerIdx == answer)
    }

    fun getGrades() : ArrayList<Boolean> {
        return gradeList
    }

    fun getQuizCount() : Int {
        return quizList.size
    }

    fun getAllGrades() : Boolean {
        var answerCheck = true
        for (grade in gradeList){
            if (grade) continue
            else {
                answerCheck = false
                break
            }
        }
        return answerCheck
    }
}