package com.example.simplequiz.config

interface BasePresenter<T> {
    fun takeView(view : T)
    fun dropView()
}