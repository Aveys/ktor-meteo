package com.aveys
const val kelvinToCelsius = 273.15
fun Int.toCelsius():Double{
    return this - kelvinToCelsius
}

fun Int.toFahrenheit():Double{
    return (this * (9/5)) - 459.67
}