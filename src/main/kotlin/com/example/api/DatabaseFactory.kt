package com.example.api


import com.example.models.UserDatas
import kotlinx.coroutines.Dispatchers
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction
import org.jetbrains.exposed.sql.transactions.transaction

object DatabaseFactory {
    fun init() {
        val driverClassName = "org.h2.Driver"
        val jdbcURL = "jdbc:postgresql://localhost:8080/postgres"
        val database = Database.connect(jdbcURL, driverClassName,user = "postgres", password = "kuba123")
        transaction(database) {
            SchemaUtils.create(UserDatas)
        }
    }

    suspend fun <T> dbQuery(block: suspend () -> T): T =
        newSuspendedTransaction(Dispatchers.IO) { block() }
}




