package com.aveys.dto

import com.aveys.model.Station

object StationsDTO {
    private val repository:MutableList<Station> = ArrayList()
    private var id = 0

    fun create(name:String, location:String):Station{
        val s = Station(id, name, location)
        id++
        repository.add(s)
        return s
    }

    fun getAll() = repository

    fun get(id:Int) = repository[id]

    fun delete(id:Int) = repository.remove(repository[id])


}