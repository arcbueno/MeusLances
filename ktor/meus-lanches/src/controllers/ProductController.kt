package br.com.arcbueno.controllers

import br.com.arcbueno.models.Product
import br.com.arcbueno.models.ProductEntity
import org.jetbrains.exposed.sql.transactions.transaction

class ProductController {

    fun getAllProducts():Iterable<Product> = transaction{
        ProductEntity.all().map(ProductEntity::toProduct);
    }

    fun addProduct(product: Product )= transaction{
        ProductEntity.new{
            this.name = product.name
            this.description = product.description
            this.category = product.category
            this.price = product.price
        }
    }

    fun getProductById(id: Int):Product = transaction{
        ProductEntity[id].toProduct();
    }

    fun deleteProduct(id: Int) = transaction{
        ProductEntity[id].delete();
    }
}