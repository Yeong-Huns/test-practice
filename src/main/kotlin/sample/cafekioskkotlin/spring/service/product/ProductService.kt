package sample.cafekioskkotlin.spring.service.product

import org.springframework.stereotype.Service
import sample.cafekioskkotlin.spring.domain.product.ProductSellingType
import sample.cafekioskkotlin.spring.dto.product.response.ProductResponse
import sample.cafekioskkotlin.spring.repository.product.ProductRepository

/**
 *packageName    : sample.cafekioskkotlin.spring.service
 * fileName       : ProductService
 * author         : Yeong-Huns
 * date           : 2025-02-23
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2025-02-23        Yeong-Huns       최초 생성
 */
@Service
class ProductService (
    private val productRepository: ProductRepository,
){
    fun getSellingProduct(): List<ProductResponse>{
        return productRepository.findAllBySellingStatusIn(ProductSellingType.Companion.forDisplay())
            .map(ProductResponse.Companion::fromProduct)
    }
}