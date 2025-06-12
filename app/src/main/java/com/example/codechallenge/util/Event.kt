package com.example.codechallenge.util

import androidx.lifecycle.Observer

open class Event<out T>(private val content : T){

    var hasBeenHandled = false
        private set

    /** Returns null if content is already handled */
    fun getContentIfNotHandled():T?{
        return if (hasBeenHandled) {
            null
        } else{
            hasBeenHandled = true
            content
        }
    }

    /** return the content*/
    fun peekContent():T = content
}

/** Observer used to check if the content is already been handled */
class EventObserver<T>(private val onEventUnHandledContent: (T) -> Unit): Observer<Event<T>>{

    override fun onChanged(event: Event<T>?) {
        event?.getContentIfNotHandled()?.let {
            onEventUnHandledContent(it)
        }
    }

}