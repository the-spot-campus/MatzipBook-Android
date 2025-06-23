package com.matzip.domain.entity.jwt

data class MatzipJwtResponseVo (
    val accessToken : String = "",
    val refreshToken : String = "",
) {
    val isTokenValid = accessToken.isNotBlank() && refreshToken.isNotBlank()
}