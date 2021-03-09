package br.com.arcbueno.controllers

import br.com.arcbueno.models.Category
import br.com.arcbueno.models.CategoryEntity
import org.jetbrains.exposed.sql.transactions.transaction

class CategoryController {

    fun getAllCategories():Iterable<Category> = transaction{
        CategoryEntity.all().map(CategoryEntity::toCategory);
    }

    fun addCategory(category: Category )= transaction{
        CategoryEntity.new{
            this.name = category.name
            this.description = category.description
        }
    }

    fun getCategoryById(id: Int):Category = transaction{
        CategoryEntity[id].toCategory();
    }

    fun deleteCategory(id: Int) = transaction{
        CategoryEntity[id].delete();
    }
}