package com.matzip.presentation.module

import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    // TODO: 앞으로 사용될 Repository를 여기에 정의
}