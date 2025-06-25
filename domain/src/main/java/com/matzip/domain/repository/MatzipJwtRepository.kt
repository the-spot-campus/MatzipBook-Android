package com.matzip.domain.repository

import com.matzip.domain.base.ApiState
import com.matzip.domain.entity.jwt.MatzipJwtResponseVo
import com.matzip.domain.request.SaveMatzipJwtRequestVo
import kotlinx.coroutines.flow.Flow

interface MatzipJwtRepository {

    suspend fun saveAccessTokenAndRefreshToken(request: SaveMatzipJwtRequestVo): Flow<Boolean>
    fun getAccessToken(): Flow<String>
    fun getRefreshToken(): Flow<String>
    suspend fun reIssueToken(request : String): Flow<ApiState<MatzipJwtResponseVo>>
    suspend fun preloadTokens() // 미리 토큰을 로드하는 함수
    suspend fun getCachedAccessToken(): String?
    suspend fun getCachedRefreshToken(): String?

}