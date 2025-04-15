package com.kokoo.base

import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.serialization.Serializable

fun Application.configureSerialization() {
    install(ContentNegotiation) {
        json()
    }
    routing {
        get("/examples") {
            call.respond(Example("kokoo", true))
        }
    }
}

@Serializable
data class Example(val id: String, val flag: Boolean)