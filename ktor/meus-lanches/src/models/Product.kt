package br.com.arcbueno.models

import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable

object Products : IntIdTable() {
    val name = varchar("name", 255)
    val description = varchar("description", 255)
    val category = varchar("category", 255)
    val price = double("price")
}

class ProductEntity(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<ProductEntity>(Products)

    var name by Products.name
    var description by Products.description
    var category by Products.category
    var price by Products.price

    override fun toString(): String = "Product($name)"

    fun toProduct() = Product(id.value, name, description, category, price);
}

data class Product(
    val id: Int,
    val name: String,
    val description: String,
    val category: String,
    val price: Double
)