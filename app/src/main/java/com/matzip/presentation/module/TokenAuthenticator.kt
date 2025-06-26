package com.matzip.presentation.module

import android.util.Log
import com.matzip.domain.base.ApiState
import com.matzip.domain.entity.jwt.MatzipJwtResponseVo
import com.matzip.domain.repository.MatzipJwtRepository
import com.matzip.domain.request.SaveMatzipJwtRequestVo
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Route
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TokenAuthenticator @Inject constructor(
    private val matzipJwtRepository: MatzipJwtRepository,
) : Authenticator {
    private val mutex = Mutex()

    override fun authenticate(route: Route?, response: okhttp3.Response): Request? = runBlocking {
        val accessToken = matzipJwtRepository.getAccessToken()
        val refreshToken = matzipJwtRepository.getRefreshToken()

        // 동기화된 블록을 사용하여 토큰 갱신 작업을 안전하게 수행
        mutex.withLock {
            if (refreshTokenIfNeeded(accessToken, refreshToken)) {
                Log.d("RETROFIT","TokenAuthenticator - authenticate() called / 중단된 API 재요청")
                response.request
                    .newBuilder()
                    .removeHeader("Authorization")
                    .header(
                        "Authorization",
                        "Bearer ${matzipJwtRepository.getAccessToken()}"
                    )
                    .build()
            } else null
        }
    }


    private suspend fun refreshTokenIfNeeded(
        access: String,
        refresh: String
    ): Boolean {
        val newAccess = matzipJwtRepository.getAccessToken()
        // 토큰 재발급
        return if (access != newAccess) true else {
            Log.d("RETROFIT","TokenAuthenticator - authenticate() called / 토큰 만료. 토큰 Refresh 요청: $refresh")
            var matzipJwtToken = MatzipJwtResponseVo("", "")
            matzipJwtRepository.reIssueToken(refresh).collect { state ->
                when(state) {
                    is ApiState.Loading -> { }
                    is ApiState.Success -> {
                        matzipJwtToken = state.data
                        return@collect
                    }
                    else -> {
                        return@collect
                    }
                }
            }

            val saveMatzipJwtRequestVo = SaveMatzipJwtRequestVo(matzipJwtToken.accessToken, matzipJwtToken.refreshToken)

            matzipJwtRepository.saveAccessTokenAndRefreshToken(saveMatzipJwtRequestVo).first()
            matzipJwtToken.isTokenValid.apply {
                if(!this) Log.d("RETROFIT","TokenAuthenticator - verifyTokenIsRefreshed() called / 토큰 갱신 실패.")
            }
        }
    }
}