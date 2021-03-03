package br.com.arcbueno.routes

import io.ktor.routing.*

fun Routing.apiRoute(){
    route("/api/v1"){
        products()
    }
}