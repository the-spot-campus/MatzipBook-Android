package com.matzip.domain.request

data class SaveMatzipJwtRequestVo(
    val accessToken : String,
    val refreshToken : String
)