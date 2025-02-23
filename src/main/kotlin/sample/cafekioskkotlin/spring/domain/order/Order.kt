package sample.cafekioskkotlin.spring.domain.order

import jakarta.persistence.CascadeType
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.OneToMany
import jakarta.persistence.Table
import sample.cafekioskkotlin.spring.domain.common.BaseEntity
import sample.cafekioskkotlin.spring.domain.orderProduct.OrderProduct
import sample.cafekioskkotlin.spring.domain.product.Product
import java.time.LocalDateTime

/**
 *packageName    : sample.cafekioskkotlin.spring.domain.order
 * fileName       : Order
 * author         : Yeong-Huns
 * date           : 2025-02-23
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2025-02-23        Yeong-Huns       최초 생성
 */
@Table(name = "orders")
@Entity
class Order(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Enumerated(EnumType.STRING)
    var orderStatus: OrderStatus,

    val totalPrice: Int,

    val registeredDateTime: LocalDateTime,

    @OneToMany(mappedBy = "order", cascade = [CascadeType.ALL])
    var orderProducts: MutableList<OrderProduct> = mutableListOf(),

    ) : BaseEntity() {

    companion object {
        fun create(products: List<Product>, registeredDate: LocalDateTime): Order {
            return Order(products, registeredDate)
        }
    }

    constructor(products: List<Product>, registeredDate: LocalDateTime) : this(
        orderStatus = OrderStatus.INIT,
        totalPrice = products.sumOf { it.price },
        registeredDateTime = registeredDate,
        orderProducts = mutableListOf()
    ) {
        this.orderProducts = products.asSequence()
            .map{ OrderProduct( order = this,  product = it) }
            .toMutableList()
    }
}