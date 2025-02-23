package sample.cafekioskkotlin.spring.dto.order.response

import jakarta.persistence.CascadeType
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.OneToMany
import sample.cafekioskkotlin.spring.domain.order.Order
import sample.cafekioskkotlin.spring.domain.order.OrderStatus
import sample.cafekioskkotlin.spring.domain.orderProduct.OrderProduct
import sample.cafekioskkotlin.spring.dto.product.response.ProductResponse
import java.time.LocalDateTime

/**
 *packageName    : sample.cafekioskkotlin.spring.dto.order.response
 * fileName       : OrderResponse
 * author         : Yeong-Huns
 * date           : 2025-02-23
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2025-02-23        Yeong-Huns       최초 생성
 */
data class OrderResponse(
    val id: Long,
    val totalPrice: Int,
    val registeredDateTime: LocalDateTime,
    val products: List<ProductResponse>
) {
    companion object {
        fun fromOrder(order: Order): OrderResponse {
            return OrderResponse(
                id = order.id!!,
                totalPrice = order.totalPrice,
                registeredDateTime = order.registeredDateTime,
                products = order.orderProducts.asSequence()
                    .map(OrderProduct::product)
                    .map(ProductResponse::fromProduct)
                    .toList()
            )
        }
    }
}