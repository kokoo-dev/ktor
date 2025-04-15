package com.kokoo.rdbms

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.jetbrains.exposed.sql.Database

fun Application.configureDatabases() {
    val database = Database.connect(
        url = "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1",
        user = "root",
        driver = "org.h2.Driver",
        password = "",
    )
    val userService = UserService(database)

    routing {
        post("/users") {
            val user = call.receive<UserDto>()
            val id = userService.create(user)

            call.respond(HttpStatusCode.Created, id)
        }

        get("/users") {
            val users = userService.getAll()

            call.respond(HttpStatusCode.OK, users)
        }
    }
}
