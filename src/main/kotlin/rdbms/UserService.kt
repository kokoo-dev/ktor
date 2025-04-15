package com.kokoo.rdbms

import kotlinx.coroutines.Dispatchers
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction
import org.jetbrains.exposed.sql.transactions.transaction

class UserService(
    database: Database
) {

    init {
        transaction(database) {
            SchemaUtils.create(Users)
        }
    }

    suspend fun create(request: UserDto): Int = dbQuery {
        Users.insert {
            it[name] = request.name
            it[age] = request.age
        }[Users.id]
    }

    suspend fun getAll(): List<UserDto> {
        return dbQuery {
            Users.selectAll()
                .map { UserDto(it[Users.name], it[Users.age]) }
        }
    }

    private suspend fun <T> dbQuery(block: suspend () -> T): T =
        newSuspendedTransaction(Dispatchers.IO) { block() }
}