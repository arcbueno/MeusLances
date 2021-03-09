package br.com.arcbueno.models

import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable

object Categories : IntIdTable() {
    val name = varchar("name", 255)
    val description = varchar("description", 255)
}

class CategoryEntity(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<CategoryEntity>(Categories)

    var name by Categories.name
    var description by Categories.description

    override fun toString(): String = "Category($name)"

    fun toCategory() = Category(id.value, name, description);
}

data class Category(
    val id: Int,
    val name: String,
    val description: String
)