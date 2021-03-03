package br.com.arcbueno

import br.com.arcbueno.controllers.bindServices
import br.com.arcbueno.routes.apiRoute
import io.ktor.application.*
import io.ktor.features.*
import io.ktor.gson.*
import io.ktor.http.*
import io.ktor.response.*
import io.ktor.request.*
import io.ktor.routing.*
import org.jetbrains.exposed.dao.exceptions.EntityNotFoundException
import org.kodein.di.ktor.di

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused") // Referenced in application.conf
@kotlin.jvm.JvmOverloads
fun Application.module(testing: Boolean = false) {
    initDB()

    install(ContentNegotiation) { gson {}}
    install(CallLogging)
    install(StatusPages){
        exception<EntityNotFoundException>{
            call.respond(HttpStatusCode.NotFound)
        }
    }
    di{
        bindServices()
    }

    routing {
        apiRoute()
    }
}

