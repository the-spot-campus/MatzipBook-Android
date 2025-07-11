package com.matzip.feature.base

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow

// 이벤트 단일 소비를 위한 래퍼 클래스
class Event<out T>(private val content: T) {

    private var hasBeenHandled = false

    fun getContentIfNotHandled(): T? {
        return if (hasBeenHandled) {
            null
        } else {
            hasBeenHandled = true
            content
        }
    }

    fun peekContent(): T = content
}

// 이벤트 처리를 위한 Flow 클래스
class EventFlow<T> {
    private val _events = MutableSharedFlow<Event<T>>(
        // 이벤트를 단일 소비로 처리하기 위해
        replay = 1,
        // 버퍼 크기를 1로 설정하여 최신 이벤트만 유지
        extraBufferCapacity = 1
    )

    val events: SharedFlow<Event<T>> = _events.asSharedFlow()

    suspend fun emit(value: T) {
        _events.emit(Event(value))
    }

    fun tryEmit(value: T) {
        _events.tryEmit(Event(value))
    }
}

suspend inline fun <T> Flow<Event<T>>.collectEvent(
    crossinline onEvent: (T) -> Unit
) {
    collect { event ->
        event.getContentIfNotHandled()?.let { value ->
            onEvent(value)
        }
    }
}