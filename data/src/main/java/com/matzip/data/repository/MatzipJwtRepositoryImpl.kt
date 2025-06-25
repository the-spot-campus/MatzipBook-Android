package com.matzip.data.repository

import android.content.Context
import com.matzip.domain.base.ApiState
import com.matzip.domain.entity.jwt.MatzipJwtResponseVo
import com.matzip.domain.repository.MatzipJwtRepository
import com.matzip.domain.request.SaveMatzipJwtRequestVo
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

//아직 token 저장소 및 api가 확실하지 않기 때문에, 이 부분은 나중에 구현할 예정입니다.
class MatzipJwtRepositoryImpl @Inject constructor(
    @ApplicationContext private val context: Context,
) : MatzipJwtRepository {
    @Volatile
    private var cachedAccessToken: String? = null
    @Volatile
    private var cachedRefreshToken: String? = null

    override suspend fun saveAccessTokenAndRefreshToken(request: SaveMatzipJwtRequestVo): Flow<Boolean> {
        TODO("Not yet implemented")
        // TODO DataStore를 사용하여 토큰을 저장하는 로직
        // cachedAccessToken = request.accessToken
        // cachedRefreshToken = request.refreshToken
    }

    override fun getAccessToken(): Flow<String> {
        TODO("Not yet implemented")
    }

    override fun getRefreshToken(): Flow<String> {
        TODO("Not yet implemented")
    }

    override suspend fun reIssueToken(request: String): Flow<ApiState<MatzipJwtResponseVo>> {
        TODO("Not yet implemented")
    }

    override suspend fun preloadTokens() {
        // TODO DataStore를 사용하여 토큰을 미리 캐시하는 로직
//        val prefs = context.dataStore.data.first()
//        cachedAccessToken = prefs[ACCESS_TOKEN_KEY]
//        cachedRefreshToken = prefs[REFRESH_TOKEN_KEY]

    }

    override suspend fun getCachedAccessToken(): String? = cachedAccessToken
    override suspend fun getCachedRefreshToken(): String? = cachedRefreshToken
}