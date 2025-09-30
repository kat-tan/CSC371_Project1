package com.example.csc371_kat_tan.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.csc371_kat_tan.R

// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Thin,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    )
)

// Custom text!
val FugazOne = FontFamily(
    Font(R.font.fugaz_one, FontWeight.Normal),
    Font(R.font.fugaz_one, FontWeight.Bold)
)

val AppTypography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FugazOne,
        fontSize = 16.sp
    ),
    titleLarge = TextStyle(
        fontFamily = FugazOne,
        fontWeight = FontWeight.Bold,
        fontSize = 22.sp
    )
)