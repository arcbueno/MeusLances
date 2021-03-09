package br.com.arcbueno.models

import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.Column

object Orders : IntIdTable() {
    val code = varchar("code", 255)
    val productsIds = text("productsIds")
    val totalPrice = double("totalPrice")
    val discountCode = text("discountCode").nullable()
    val discountValue: Column<Double?> = double("discountValue").nullable()
    val finalPrice = double("finalPrice")
}

class OrderEntity(id: EntityID<Int>) : IntEntity(id) {

    companion object : IntEntityClass<OrderEntity>(Orders){
        const val SEPARATOR = ",";
    }

    var code by Orders.code
    var productsIds by Orders.productsIds.transform(
        {a -> a.joinToString(SEPARATOR)},
        { str -> str.split(SEPARATOR).map { it.toIntOrNull() }.toTypedArray().toList() }
    )
    var totalPrice by Orders.totalPrice
    var discountCode by Orders.discountCode
    var discountValue : Double? by Orders.discountValue
    var finalPrice by Orders.finalPrice

    override fun toString(): String = "Order($code)"

    fun toOrder() = Order(id.value, code, productsIds, totalPrice, discountCode, discountValue, finalPrice);
}

data class Order(
    val id: Int,
    val code: String,
    val productsIds: List<Int?>,
    val totalPrice: Double,
    val discountCode: String?,
    val discountValue: Double?,
    val finalPrice: Double
)