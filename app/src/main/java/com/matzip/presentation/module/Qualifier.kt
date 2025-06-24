package com.matzip.presentation.module

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class AuthOkHttpClient //header에 Authorization을 추가하는 OkHttpClient

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class AuthRetrofit //header에 Authorization을 추가하는 Retrofit

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class NormalRetrofit //header에 Authorization을 추가하지 않는 Retrofit