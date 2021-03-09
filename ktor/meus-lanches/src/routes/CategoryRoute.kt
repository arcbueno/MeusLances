package br.com.arcbueno.routes

import br.com.arcbueno.controllers.CategoryController
import br.com.arcbueno.controllers.ProductController
import br.com.arcbueno.models.Category
import br.com.arcbueno.models.Product
import io.ktor.application.*
import io.ktor.features.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import org.kodein.di.instance
import org.kodein.di.ktor.di

fun Route.categories(){
    val routeName = "categories"
    val categoryController by di().instance<CategoryController>();

    get(routeName){
        val allCategories = categoryController.getAllCategories();
        call.respond(allCategories)
    }

    get("$routeName/{id}"){
        val category = categoryController.getCategoryById(call.parameters["id"]?.toIntOrNull() ?: throw NotFoundException());
        call.respond(category)
    }

    post(routeName){
        val categoryRequest = call.receive<Category>()
        categoryController.addCategory(categoryRequest)
        call.respond(HttpStatusCode.Accepted)
    }

    delete("$routeName/{id}"){
        val product = categoryController.deleteCategory(call.parameters["id"]?.toIntOrNull() ?: throw NotFoundException());
        call.respond(HttpStatusCode.OK)
    }
}