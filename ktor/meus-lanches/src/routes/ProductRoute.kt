package br.com.arcbueno.routes

import br.com.arcbueno.controllers.ProductController
import br.com.arcbueno.models.Product
import io.ktor.application.*
import io.ktor.features.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import org.kodein.di.instance
import org.kodein.di.ktor.di

fun Route.products(){
    val routeName = "products"
    val productController by di().instance<ProductController>();

    get(routeName){
        val allProducts = productController.getAllProducts();
        call.respond(allProducts)
    }

    get("$routeName/{id}"){
        val product = productController.getProductById(call.parameters["id"]?.toIntOrNull() ?: throw NotFoundException());
        call.respond(product)
    }

    post(routeName){
        val productRequest = call.receive<Product>()
        productController.addProduct(productRequest)
        call.respond(HttpStatusCode.Accepted)
    }

    delete("$routeName/{id}"){
        val product = productController.deleteProduct(call.parameters["id"]?.toIntOrNull() ?: throw NotFoundException());
        call.respond(HttpStatusCode.OK)
    }
}