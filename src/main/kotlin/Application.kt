package com.kokoo

import com.kokoo.base.configureRouting
import com.kokoo.base.configureSerialization
import com.kokoo.rdbms.configureDatabases
import io.ktor.server.application.*

fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}

fun Application.module() {
    configureSerialization()
    configureRouting()
    configureDatabases()
}
