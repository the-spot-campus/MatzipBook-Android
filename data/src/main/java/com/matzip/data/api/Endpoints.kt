package com.matzip.data.api

object Endpoints {
    // API 경로 정의 클래스
    object AUTH{
        private const val AUTH = "/auth"
        const val LOGIN_KAKAO = "$AUTH/login/kakao"
    }
}