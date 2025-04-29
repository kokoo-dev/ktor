package com.kokoo

import com.kokoo.base.configureRouting
import com.kokoo.base.configureSerialization
import com.kokoo.mongo.configureMongo
import com.kokoo.rdbms.configureRdbms
import io.ktor.server.application.*

fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}

fun Application.module() {
    configureSerialization()
    configureRouting()
    configureRdbms()
    configureMongo()
}
