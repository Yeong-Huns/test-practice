package sample.cafekioskkotlin.spring.dto.product.request

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Positive
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
    @field:NotNull(message = "상품 타입은 필수입니다.")
    val type: ProductType?,

    @field:NotNull(message = "상품 판매상태는 필수입니다.")
    val sellingStatus: ProductSellingType?,

    @field:NotBlank(message = "상품 이름은 필수입니다.")
    val name: String,

    @field:Positive(message = "상품 가격은 양수여야 합니다.")
    val price: Int
) {

    fun toProduct(lastProductNumber: String): Product {
        return Product(
            name = this.name,
            productNumber = lastProductNumber,
            productType = type!!,
            sellingStatus = sellingStatus!!,
            price = this.price
        )
    }

}
