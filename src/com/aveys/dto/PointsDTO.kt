package com.aveys.dto

import com.aveys.model.Point
import java.time.LocalDate

object PointsDTO {
    private val repository:MutableList<Point> = ArrayList()
    private var id = 0

    fun create(stationNumber:Int, date:LocalDate, value:Int):Point{
        val p = Point(id,stationNumber, date, value)
        id++
        repository.add(p)
        return p
    }

    fun getAll() = repository
    fun get(id:Int) = repository[id]
}