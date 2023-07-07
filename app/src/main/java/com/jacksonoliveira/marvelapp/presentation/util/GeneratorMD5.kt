package com.jacksonoliveira.marvelapp.presentation.util

import com.jacksonoliveira.marvelapp.BuildConfig.MARVEL_PRIVATE_API_KEY
import com.jacksonoliveira.marvelapp.BuildConfig.MARVEL_PUBLIC_API_KEY
import java.math.BigInteger
import java.security.MessageDigest

fun generatorHashMd5(): String {
    val input = "$TS_DEFAULT${MARVEL_PRIVATE_API_KEY}${MARVEL_PUBLIC_API_KEY}"
    val md = MessageDigest.getInstance(MD5)
    return BigInteger(SIG_NUM, md.digest(input.toByteArray()))
        .toString(RADIX)
        .padStart(LENGTH_PAD, VALUE_ZERO_CHAR)
}

private const val MD5 = "MD5"
private const val SIG_NUM = 1
const val TS_DEFAULT = 1
private const val RADIX = 16
private const val LENGTH_PAD = 32
private const val VALUE_ZERO_CHAR = '0'