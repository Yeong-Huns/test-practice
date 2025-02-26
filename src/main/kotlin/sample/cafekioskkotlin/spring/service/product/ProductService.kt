package sample.cafekioskkotlin.spring.service.product

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import sample.cafekioskkotlin.spring.domain.product.ProductSellingType
import sample.cafekioskkotlin.spring.dto.product.request.ProductCreateRequest
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

    @Transactional
    fun createProduct(productRequest: ProductCreateRequest) : ProductResponse {
        //productNumber
        // 001, 002, 003, 004
        // DB 에서 마지막 저장된 Product 의 상품 번호를 읽어와서 + 1
        val nextProductNumber = createNextProductNumber()
        val savedProduct = productRepository.save(productRequest.toProduct(nextProductNumber))
        return ProductResponse.fromProduct(savedProduct)
    }

    private fun createNextProductNumber(): String{
        /* 엘비스 연산자를 활용하여 상품이 하나도 없을 경우, 마지막 번호는 000 으로 설정 */
        val lastProductNumber = productRepository.findLatestProductNumber()
            ?: "000"

        val nextProductNumber = lastProductNumber.toInt() + 1
        return String.format("%03d", nextProductNumber)
    }
}