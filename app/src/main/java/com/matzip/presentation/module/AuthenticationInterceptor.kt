package com.matzip.presentation.module

import android.util.Log
import com.matzip.domain.repository.MatzipJwtRepository
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthenticationInterceptor@Inject constructor(
    private val matzipJwtRepository: MatzipJwtRepository
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): okhttp3.Response {
        val accessToken = matzipJwtRepository.getAccessToken()

        val request = chain.request().newBuilder()
            .addHeader("Authorization", "Bearer $accessToken").build()

        Log.d("RETROFIT",
            "AuthenticationInterceptor - intercept() called / request header aadded: Authorization: Bearer $accessToken"
        ) // TODO 테스트 하면서 로그 확인 후 제거 예정
        return chain.proceed(request)
    }
}