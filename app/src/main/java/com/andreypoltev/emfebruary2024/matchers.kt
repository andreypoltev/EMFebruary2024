package com.andreypoltev.emfebruary2024

import android.util.Log
import java.util.regex.Pattern

fun isValidPhoneNumber(phoneNumber: String): Boolean {
    val pattern = Pattern.compile("^\\+7\\s\\d{3}\\s\\d{3}\\s\\d{2}\\s\\d{2}\$")
    return pattern.matcher(phoneNumber).matches()
}

fun soFarSoGood(phoneNumber: String): Boolean {
    val one = "^\\+"
    val two = "$one|${one}7"
    val three = "$two|${two} "

    val four = "$three|${three}\\d"
    val five = "$four|${four}\\d"
    val six = "$five|${five}\\d"

    val seven = "$six|${six}\\s"

    val eight = "$seven|${seven}\\d"
    val nine = "$eight|${eight}\\d"
    val ten = "$nine|${nine}\\d"

    val eleven = "$ten|${ten}\\s"

    val twelve = "$eleven|${eleven}\\d"
    val thirteen = "$twelve|${twelve}\\d"

    val fourteen = "$thirteen|${thirteen}\\s"

    val fifteen = "$fourteen|${fourteen}\\d"
    val sixteen = "$fifteen|${fifteen}\\d$"


    val pattern = Pattern.compile(sixteen)
    Log.d("LOG", sixteen)
//    val pattern = Pattern.compile("^\\+\\d{1,3}\\s?\\d{1,3}\\s?\\d{1,4}\\s?\\d{1,4}\n")
    return pattern.matcher(phoneNumber).matches()
}