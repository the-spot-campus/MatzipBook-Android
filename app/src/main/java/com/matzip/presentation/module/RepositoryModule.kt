package com.matzip.presentation.module

import android.content.Context
import com.matzip.data.repository.MatzipJwtRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.runBlocking
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    // TODO: 앞으로 사용될 Repository를 여기에 정의
}

@Module
@InstallIn(SingletonComponent::class)
object JwtModule {

    @Provides
    @Singleton
    fun provideMatzipJwtRepository(
        @ApplicationContext context: Context,
    ): MatzipJwtRepositoryImpl {
        val repository = MatzipJwtRepositoryImpl(context)
        runBlocking {
            repository.preloadTokens()
        }
        return repository
    }
}