package com.example.simplequiz.main

class MainActivityModel {
    // easy, medium, hard
    private val isReady = arrayListOf<Boolean>(true, false, false)
    private val isSolved = arrayListOf<Boolean>(false, false, false)
    private val clearList = arrayListOf<Boolean>(false, false, false)

    fun clear(){
        isReady.clear()
        isReady.addAll(clearList)
        isReady[0] = true
        isSolved.clear()
        isSolved.addAll(clearList)
    }

    fun solve(questNum : Int) : Boolean{
        return if (isAvailable(questNum)) {
            isSolved[questNum - 1] = true
            true
        } else {
            false
        }
    }

    fun getReady() : ArrayList<Boolean>{
        return isReady
    }

    fun getSolved() : ArrayList<Boolean> {
        return isSolved
    }

    fun isAvailable(questNum : Int) : Boolean {
        return (questNum <= isReady.size && questNum > 0 && isReady[questNum - 1])
    }

    fun getState() : ArrayList<Int>{
        val tempDataList = arrayListOf<Int>()
        for (idx in 0 until isSolved.size){
            if (isReady[idx] && isSolved[idx]){
                tempDataList.add(2)
            } else if (isReady[idx] && !isSolved[idx]) {
                tempDataList.add(1)
            } else {
                tempDataList.add(0)
            }
        }
        return tempDataList
    }
}