import com.aveys.controller.Controller
import com.google.gson.GsonBuilder
import io.ktor.application.*
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.http.*
import io.ktor.gson.*
import io.ktor.features.*
import io.ktor.request.receive
import io.ktor.util.KtorExperimentalAPI

data class PointWrapper(var stationNumber: Int?, var date: String?, var value: Int?)
data class StationWrapper(var name: String?, var location: String?)


fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

@KtorExperimentalAPI
@Suppress("unused") // Referenced in application.conf
@kotlin.jvm.JvmOverloads
fun Application.module(testing: Boolean = false) {
    val controller = Controller()
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
        /*exception<Throwable> { cause ->
            call.respond(message = "Server Error ${cause.message}", status = HttpStatusCode.InternalServerError)
        }
        */
        exception<NotFoundException> { call.respond(HttpStatusCode.NotFound, "Object not found :  ${it.message}") }
    }

    routing {
        get("/") {
            val name = call.parameters["name"]
            val text = if (name == null) {
                "Welcome to Ktor-Meteo !"
            } else {
                "$name, Welcome to Ktor-Meteo !"
            }

            call.respond(mapOf("home" to text))
        }
        /**
         * Create Station
         */
        post("/stations") {
            val s = call.receive<StationWrapper>()
            val station = controller.createStation(s.location, s.name)
            call.respond(station)
        }
        /**
         * List Stations
         */
        get("/stations") {
            call.respond(controller.getStation())
        }
        /**
         * Get a station detail
         */
        get("/stations/{id}") {
            val id = call.parameters["id"] ?: throw IllegalArgumentException("Id could not be null")
            val station = controller.getStation(id.toInt()).firstOrNull()
                ?: throw NotFoundException("Station with id=$id not found")
            call.respond(station)
        }
        /**
         * Delete station by ID
         */
        delete("/stations/{id}") {
            val id = call.parameters["id"] ?: throw IllegalArgumentException("Id could not be null")
            call.respond(controller.deleteStation(id.toInt()))
        }
        /**
         * Get list of points from a specific station
         * param : startDate point from this date to now. Could be null -> 14 more recent
         */
        get("/points/{id}") {
            TODO()
        }
        /**
         * Put points
         */
        put("/points") {
            TODO()
        }
        /**
         * Get list of points from all stations
         * param : startDate points from this date to now. Could be null -> 14 more recent
         */
        get("/points") {
            TODO()
        }
        /**
         * get average t° by day
         * param : unit -> farenheit, celsius, kelvins
         */
        get("/averageByDay") {
            call.respond(controller.averageByDay())
        }

        post("/init"){
            call.respond(controller.init())
        }
    }
}

