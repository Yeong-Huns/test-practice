package sample.cafekioskkotlin.spring.service

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.transaction.annotation.Transactional
import sample.cafekioskkotlin.spring.domain.product.Product
import sample.cafekioskkotlin.spring.domain.product.ProductSellingType
import sample.cafekioskkotlin.spring.domain.product.ProductType
import sample.cafekioskkotlin.spring.dto.product.request.ProductCreateRequest
import sample.cafekioskkotlin.spring.repository.product.ProductRepository
import sample.cafekioskkotlin.spring.service.product.ProductService

/**
 *packageName    : sample.cafekioskkotlin.spring.service
 * fileName       : ProductServiceTest
 * author         : Yeong-Huns
 * date           : 2025-02-23
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2025-02-23        Yeong-Huns       최초 생성
 */
@Transactional
@ActiveProfiles("test")
@SpringBootTest
class ProductServiceTest(
    @Autowired
    private val productRepository: ProductRepository,
    @Autowired
    private val productService: ProductService
) {
    @DisplayName("신규 상품을 등록한다. 상품번호는 가장 최근 상품의 상품번호에서 1 증가한 값이다. ")
    @Test
    fun createProduct() {
        /* given */
        val product1 = Product(
            productNumber = "001",
            productType = ProductType.HANDMADE,
            sellingStatus = ProductSellingType.SELLING,
            name = "아메리카노",
            price = 4000,
        )
        val product2 = Product(
            productNumber = "002",
            productType = ProductType.HANDMADE,
            sellingStatus = ProductSellingType.HOLD,
            name = "카페라떼",
            price = 4500,
        )
        val lastProductNumber = "003"
        val product3 = Product(
            productNumber = lastProductNumber,
            productType = ProductType.HANDMADE,
            sellingStatus = ProductSellingType.STOP_SELLING,
            name = "팥빙수",
            price = 7000,
        )

        productRepository.saveAll(listOf(product1, product2, product3))

        val productCreateRequest = ProductCreateRequest(
            type = ProductType.HANDMADE,
            sellingStatus = ProductSellingType.SELLING,
            name = "카푸치노",
            price = 5000,
        )

        /* when */
        val productResponse = productService.createProduct(productCreateRequest)

        /* then */
        assertThat(productResponse).isNotNull() // null 여부 검사
        assertThat(productResponse)
            .extracting("productNumber", "type", "sellingStatus", "name", "price")
            .contains("004", ProductType.HANDMADE, ProductSellingType.SELLING, "카푸치노", 5000)
    }

    @DisplayName("상품이 하나도 없는 경우 신규 상품을 등록하면 상품번호는 001 이다. ")
    @Test
    fun createProductWhenProductIsEmpty() {
        /* given */
        val productCreateRequest = ProductCreateRequest(
            type = ProductType.HANDMADE,
            sellingStatus = ProductSellingType.SELLING,
            name = "카푸치노",
            price = 5000,
        )

        /* when */
        val productResponse = productService.createProduct(productCreateRequest)

        /* then */
        assertThat(productResponse).isNotNull() // null 여부 검사
        assertThat(productResponse)
            .extracting("productNumber", "type", "sellingStatus", "name", "price")
            .contains("001", ProductType.HANDMADE, ProductSellingType.SELLING, "카푸치노", 5000)
    }

}