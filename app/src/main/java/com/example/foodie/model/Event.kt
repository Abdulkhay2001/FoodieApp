package com.example.foodie.model

class Event<T>(private val content: T) {
    private var isHandled = false

    fun getContentIdNotHandled(): T? {
        return if (isHandled) {
            null
        } else {
            isHandled = true
            content
        }
    }

    fun peekContent(): T = content
}