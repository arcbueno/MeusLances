package br.com.arcbueno.controllers

import br.com.arcbueno.models.Category
import br.com.arcbueno.models.CategoryEntity
import br.com.arcbueno.models.Order
import br.com.arcbueno.models.OrderEntity
import org.jetbrains.exposed.sql.transactions.transaction

class OrderController {

    fun getAllOrders():Iterable<Order> = transaction{
        OrderEntity.all().map(OrderEntity::toOrder);
    }

    fun addOrder(order: Order )= transaction{
        OrderEntity.new{
            this.code = order.code
            this.productsIds = order.productsIds
            this.totalPrice = order.totalPrice
            this.discountCode = order.discountCode.orEmpty()
            this.discountValue = order.discountValue
            this.finalPrice = order.finalPrice
        }
    }

    fun getOrderById(id: Int):Order = transaction{
        OrderEntity[id].toOrder();
    }

    fun deleteOrder(id: Int) = transaction{
        OrderEntity[id].delete();
    }
}