package sample.cafekioskkotlin.spring.domain.stock

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import sample.cafekioskkotlin.spring.domain.common.BaseEntity

/**
 *packageName    : sample.cafekioskkotlin.spring.domain.stock
 * fileName       : Stock
 * author         : Yeong-Huns
 * date           : 2025-02-24
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2025-02-24        Yeong-Huns       최초 생성
 */
@Entity
class Stock (
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    val productNumber: String,

    var quantity: Int,
): BaseEntity() {

    fun isQuantityLessThan(quantity: Int): Boolean {
        return this.quantity < quantity
    }

    fun deductQuantity(quantity: Int) {
        if(isQuantityLessThan(quantity)) {
            throw IllegalArgumentException("차감할 재고 수량이 없습니다.")
        }
        this.quantity -= quantity
    }

    companion object {
        fun create(productNumber: String, quantity: Int): Stock {
            return Stock(productNumber = productNumber, quantity = quantity)
        }
    }

}