package sample.cafekioskkotlin.spring.dto.product.request

import sample.cafekioskkotlin.spring.domain.product.Product
import sample.cafekioskkotlin.spring.domain.product.ProductSellingType
import sample.cafekioskkotlin.spring.domain.product.ProductType

/**
 *packageName    : sample.cafekioskkotlin.spring.dto.product.request
 * fileName       : ProductCreateRequest
 * author         : Yeong-Huns
 * date           : 2025-02-26
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2025-02-26        Yeong-Huns       최초 생성
 */
data class ProductCreateRequest(
    val type: ProductType,
    val sellingStatus: ProductSellingType,
    val name: String,
    val price: Int
) {

    fun toProduct(lastProductNumber: String): Product {
        return Product(
            name = this.name,
            productNumber = lastProductNumber,
            productType = type,
            sellingStatus = sellingStatus,
            price = this.price,
        )
    }

}
