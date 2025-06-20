package com.matzip.domain.base

sealed class ApiState<out T> {
    // API 공통 상태 정의
    object Loading : ApiState<Nothing>()
    data class Success<T>(val data : T) : ApiState<T>()
    data class Error<T>(val data : T?, val errorCode : String) : ApiState<T>()
}