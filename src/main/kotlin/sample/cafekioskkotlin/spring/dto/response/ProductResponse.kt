package sample.cafekioskkotlin.spring.dto.response

import sample.cafekioskkotlin.spring.domain.product.Product
import sample.cafekioskkotlin.spring.domain.product.ProductSellingType
import sample.cafekioskkotlin.spring.domain.product.ProductType

/**
 *packageName    : sample.cafekioskkotlin.spring.dto.response
 * fileName       : ProductResponse
 * author         : Yeong-Huns
 * date           : 2025-02-23
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2025-02-23        Yeong-Huns       최초 생성
 */
data class ProductResponse(
    val id: Long,
    val productNumber: String,
    val type: ProductType,
    val sellingStatus: ProductSellingType,
    val name: String,
    val price: Int,
) {
    companion object {
        fun fromProduct(product: Product) : ProductResponse {
            return ProductResponse(
                id = product.id!!,
                productNumber = product.productNumber,
                type = product.productType,
                sellingStatus = product.sellingStatus,
                name = product.name,
                price = product.price,
            )
        }
    }
}
