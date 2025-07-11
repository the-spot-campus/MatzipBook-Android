package com.matzip.feature.designSystem

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.matzip.feature.R

val matZipFontFamily = FontFamily(
    Font(R.font.pretendard_bold, FontWeight.Bold),
    Font(R.font.pretendard_light, FontWeight.Light),
    Font(R.font.pretendard_medium, FontWeight.Medium),
    Font(R.font.pretendard_semi_bold, FontWeight.SemiBold),
    Font(R.font.pretendard_regular, FontWeight.Normal),
)

object MatZipTypography {
    val h1: TextStyle = TextStyle(
        fontFamily = matZipFontFamily,
        fontWeight = FontWeight.Bold,
        lineHeight = 43.2.sp,
        fontSize = 32.sp
    )

    val h2: TextStyle = TextStyle(
        fontFamily = matZipFontFamily,
        fontWeight = FontWeight.Bold,
        lineHeight = 37.8.sp,
        fontSize = 28.sp
    )

    val title1: TextStyle = TextStyle(
        fontFamily = matZipFontFamily,
        fontWeight = FontWeight.Bold,
        lineHeight = 32.4.sp,
        fontSize = 24.sp
    )

    val title2: TextStyle = TextStyle(
        fontFamily = matZipFontFamily,
        fontWeight = FontWeight.Bold,
        lineHeight = 27.sp,
        fontSize = 20.sp
    )

    val title3: TextStyle = TextStyle(
        fontFamily = matZipFontFamily,
        fontWeight = FontWeight.Bold,
        lineHeight = 21.6.sp,
        fontSize = 16.sp
    )

    val title4: TextStyle = TextStyle(
        fontFamily = matZipFontFamily,
        fontWeight = FontWeight.Bold,
        lineHeight = 18.2.sp,
        fontSize = 14.sp
    )

    val title5: TextStyle = TextStyle(
        fontFamily = matZipFontFamily,
        fontWeight = FontWeight.SemiBold,
        lineHeight = 18.2.sp,
        fontSize = 14.sp
    )

    val title6: TextStyle = TextStyle(
        fontFamily = matZipFontFamily,
        fontWeight = FontWeight.Bold,
        lineHeight = 15.6.sp,
        fontSize = 12.sp
    )

    val title7: TextStyle = TextStyle(
        fontFamily = matZipFontFamily,
        fontWeight = FontWeight.SemiBold,
        lineHeight = 15.6.sp,
        fontSize = 12.sp
    )

    val body1: TextStyle = TextStyle(
        fontFamily = matZipFontFamily,
        fontWeight = FontWeight.Normal,
        lineHeight = 21.6.sp,
        fontSize = 16.sp
    )

    val body2: TextStyle = TextStyle(
        fontFamily = matZipFontFamily,
        fontWeight = FontWeight.Normal,
        lineHeight = 18.9.sp,
        fontSize = 14.sp
    )

    val ex1: TextStyle = TextStyle(
        fontFamily = matZipFontFamily,
        fontWeight = FontWeight.Normal,
        lineHeight = 15.6.sp,
        fontSize = 12.sp
    )

    val ex2: TextStyle = TextStyle(
        fontFamily = matZipFontFamily,
        fontWeight = FontWeight.Normal,
        lineHeight = 13.sp,
        fontSize = 10.sp
    )

}
