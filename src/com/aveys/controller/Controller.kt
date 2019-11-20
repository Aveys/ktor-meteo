package com.aveys.controller

import DateProgression
import com.aveys.dto.PointsDTO
import com.aveys.dto.StationsDTO
import com.aveys.model.Station
import toCelsius
import java.time.LocalDate
import kotlin.math.roundToInt
import kotlin.random.Random

class Controller {

    private val pointDTO = PointsDTO
    private val stationDTO = StationsDTO

    init {
        this.init()
    }

    fun createStation(location: String?, name: String?): Station {
        if (location == null || name == null) {
            throw IllegalArgumentException("One of the field is null")
        } else {
            return stationDTO.create(location = location, name = name)
        }
    }

    fun getStation(id:Int? = null): List<Station>{
        return if(id == null){
            stationDTO.getAll().filter { true }
        } else {
            stationDTO.getAll().filter { it.id == id }
        }
    }

    fun deleteStation(id: Int): Boolean {
        return stationDTO.delete(id)
    }

    operator fun LocalDate.rangeTo(other: LocalDate) = DateProgression(this, other)
    fun init(): Boolean {
        stationDTO.create("Aeroport de Mirabel", "Mirabel")
        stationDTO.create("Aeroport YUL", "Dorval")
        val startDate = LocalDate.of(2019, 10, 1)
        val endDate = LocalDate.of(2019, 10, 31)
        for(i in startDate..endDate step 1){
            pointDTO.create(1,i, Random.nextInt(263,283))
            pointDTO.create(2,i, Random.nextInt(263,283))
        }
        return true
    }

    fun averageByDay(): Map<LocalDate, Double> {
        return PointsDTO.getAll().groupBy { it.date }.map { it.key to it.value.map { point -> point.value.toCelsius().roundToInt() }.average() }.toMap()
    }


}