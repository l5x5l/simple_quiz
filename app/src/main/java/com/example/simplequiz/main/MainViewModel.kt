package com.example.simplequiz.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData

class MainViewModel(application: Application) : AndroidViewModel(application) {
    private val model = MainActivityModel()
    val stateList : MutableLiveData<ArrayList<Int>> = MutableLiveData(arrayListOf())

    // 문제 해결시 상태 변화
    fun solved(solveIdx : Int){
        if (model.solve(solveIdx)){
            val temp = model.getState()
            stateList.postValue(temp)
        }
    }

    fun initState(){
        stateList.postValue(model.getState())
    }

    fun clearState(){
        model.clear()
        stateList.postValue(model.getState())
    }

    fun isAvailable(quizIdx : Int) : Boolean {
        return model.isAvailable(quizIdx)
    }

}