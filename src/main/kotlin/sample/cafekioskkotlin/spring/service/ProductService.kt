package sample.cafekioskkotlin.spring.service

import org.springframework.stereotype.Service
import sample.cafekioskkotlin.spring.domain.product.ProductSellingType
import sample.cafekioskkotlin.spring.dto.response.ProductResponse
import sample.cafekioskkotlin.spring.repository.ProductRepository

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
        return productRepository.findAllBySellingStatusIn(ProductSellingType.forDisplay())
            .map(ProductResponse::fromProduct)
    }
}