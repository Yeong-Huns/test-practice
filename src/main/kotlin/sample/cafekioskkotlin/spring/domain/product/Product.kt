package sample.cafekioskkotlin.spring.domain.product

import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import sample.cafekioskkotlin.spring.domain.common.BaseEntity

/**
 *packageName    : sample.cafekioskkotlin.spring.domain.product
 * fileName       : Product
 * author         : Yeong-Huns
 * date           : 2025-02-22
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2025-02-22        Yeong-Huns       최초 생성
 */
@Entity
class Product (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    var productNumber : String,

    @Enumerated(EnumType.STRING)
    var productType : ProductType,

    @Enumerated(EnumType.STRING)
    var sellingStatus: ProductSellingType,

    var name: String,

    var price: Int,

) : BaseEntity()