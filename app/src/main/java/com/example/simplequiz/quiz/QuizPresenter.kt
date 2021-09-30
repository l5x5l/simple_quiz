package com.example.simplequiz.quiz

class QuizPresenter : QuizContract.Presenter {

    private var quizView : QuizContract.View? = null
    private val model = QuizActivityModel()

    override fun setQuizzes() {
        val quizList = arrayListOf(model.getQuiz(0), model.getQuiz(1))
        quizView!!.setQuizFragments(quizList)
        quizView!!.setQuizCountView(model.getQuizCount())
    }

    override fun getQuizCount(): Int {
        return model.getQuizCount()
    }

    override fun getAllGrades(): Boolean {
        return model.getAllGrades()
    }

    override fun gradeQuiz(userAnswer: Int, answer: Int) {
        model.gradeQuiz(userAnswer, answer)
    }

    override fun goToNext(idx: Int) {
        if (idx < model.getQuizCount() - 1){
            quizView!!.goToNextQuiz(idx)
        } else if (idx == model.getQuizCount() - 1) {
            quizView!!.goToResult(model.getGrades())
        }
    }

    override fun takeView(view: QuizContract.View) {
        quizView = view
    }

    override fun dropView() {
        quizView = null
    }
}