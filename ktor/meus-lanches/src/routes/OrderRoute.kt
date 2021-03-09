package br.com.arcbueno.routes

import br.com.arcbueno.controllers.CategoryController
import br.com.arcbueno.controllers.OrderController
import br.com.arcbueno.controllers.ProductController
import br.com.arcbueno.models.Category
import br.com.arcbueno.models.Order
import br.com.arcbueno.models.Product
import io.ktor.application.*
import io.ktor.features.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import org.kodein.di.instance
import org.kodein.di.ktor.di

fun Route.orders(){
    val routeName = "orders"
    val orderController by di().instance<OrderController>();

    get(routeName){
        val allCategories = orderController.getAllOrders();
        call.respond(allCategories)
    }

    get("$routeName/{id}"){
        val category = orderController.getOrderById(call.parameters["id"]?.toIntOrNull() ?: throw NotFoundException());
        call.respond(category)
    }

    post(routeName){
        val orderRequest = call.receive<Order>()
        orderController.addOrder(orderRequest)
        call.respond(HttpStatusCode.Accepted)
    }

    delete("$routeName/{id}"){
        val product = orderController.deleteOrder(call.parameters["id"]?.toIntOrNull() ?: throw NotFoundException());
        call.respond(HttpStatusCode.OK)
    }
}