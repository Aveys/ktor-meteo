package com.aveys

import com.google.gson.GsonBuilder
import io.ktor.application.*
import io.ktor.response.*
import io.ktor.request.*
import io.ktor.routing.*
import io.ktor.http.*
import io.ktor.gson.*
import io.ktor.features.*
var id:Int = 0
fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused") // Referenced in application.conf
@kotlin.jvm.JvmOverloads
fun Application.module(testing: Boolean = false) {
    install(ContentNegotiation) {
        gson {
            register(ContentType.Application.Json, GsonConverter(GsonBuilder().create()))
        }
    }

    install(CORS) {
        method(HttpMethod.Options)
        method(HttpMethod.Put)
        method(HttpMethod.Delete)
        method(HttpMethod.Patch)
        header(HttpHeaders.Authorization)
        header("MyCustomHeader")
        allowCredentials = true
        anyHost() // @TODO: Don't do this in production if possible. Try to limit it.
    }

    install(StatusPages) {
        exception<Throwable> { cause ->
            call.respond(message = "Server Error ${cause.message}", status = HttpStatusCode.InternalServerError)
        }
    }

    routing {
        get("/") {
            val name = call.parameters["name"]
            val text = if(name == null){
                "Welcome to Ktor-Meteo !"
            } else {
                "$name, Welcome to Ktor-Meteo !"
            }

            call.respond(mapOf("home" to text ))
        }
        /**
         * Create Station
         */
        post("/stations"){

        }
        /**
         * List Stations
         */
        get("/stations"){

        }
        /**
         * Get a station detail
         */
        get("/stations/{id}"){

        }
        /**
         * Delete station by ID
         */
        delete("/stations/{id}"){

        }
        /**
         * Get list of points from a specific station
         * param : startDate point from this date to now. Could be null -> 14 more recent
         */
        get("/points/{id}"){

        }
        /**
         * Put points
         */
        put("/points"){

        }
        /**
         * Get list of points from all stations
         * param : startDate points from this date to now. Could be null -> 14 more recent
         */
        get("/points"){

        }
        /**
         * get average t° by day
         * param : unit -> farenheit, celsius, kelvins
         */
        get("/averageByDay"){

        }
    }
}

