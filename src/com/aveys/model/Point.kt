package com.aveys.model

import java.time.LocalDate

/**
 * Temperature measure POKO
 */
data class Point(var id:Int, val stationNumber:Int,  var date:LocalDate, var value:Int )