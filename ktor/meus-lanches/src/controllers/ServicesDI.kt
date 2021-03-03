package br.com.arcbueno.controllers

import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.singleton

fun DI.MainBuilder.bindServices(){
    bind<ProductController>() with singleton { ProductController() }
}