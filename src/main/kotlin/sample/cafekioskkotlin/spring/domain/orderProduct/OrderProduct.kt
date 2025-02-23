package sample.cafekioskkotlin.spring.domain.orderProduct

import jakarta.persistence.*
import sample.cafekioskkotlin.spring.domain.common.BaseEntity
import sample.cafekioskkotlin.spring.domain.order.Order
import sample.cafekioskkotlin.spring.domain.product.Product

/**
 *packageName    : sample.cafekioskkotlin.spring.domain.orderProduct
 * fileName       : OrderProduct
 * author         : Yeong-Huns
 * date           : 2025-02-23
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2025-02-23        Yeong-Huns       최초 생성
 */
@Entity
class OrderProduct (
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    val order : Order,

    @ManyToOne(fetch = FetchType.LAZY)
    val product: Product,
) : BaseEntity()