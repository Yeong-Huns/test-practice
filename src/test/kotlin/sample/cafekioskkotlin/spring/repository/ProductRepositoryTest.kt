package sample.cafekioskkotlin.spring.repository

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.tuple
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import sample.cafekioskkotlin.spring.domain.product.Product
import sample.cafekioskkotlin.spring.domain.product.ProductSellingType
import sample.cafekioskkotlin.spring.domain.product.ProductType
import sample.cafekioskkotlin.spring.repository.product.ProductRepository

/**
 *packageName    : sample.cafekioskkotlin.spring.repository
 * fileName       : ProductRepositoryTest
 * author         : Yeong-Huns
 * date           : 2025-02-23
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2025-02-23        Yeong-Huns       최초 생성
 */
/* profile 을 test 로 설정 */
@ActiveProfiles("test")
@SpringBootTest
/* @DataJpaTest */
class ProductRepositoryTest (
    @Autowired val productRepository: ProductRepository
){
    @DisplayName("원하는 판매상태를 가진 상품들을 조회한다.")
    @Test
    fun findAllBySellingStatusIn() {
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
        val product3 = Product(
            productNumber = "003",
            productType = ProductType.HANDMADE,
            sellingStatus = ProductSellingType.STOP_SELLING,
            name = "팥빙수",
            price = 7000,
        )
        productRepository.saveAll(listOf(product1, product2, product3))

        /* when */
        val products = productRepository.findAllBySellingStatusIn(listOf(ProductSellingType.SELLING, ProductSellingType.HOLD))

        /* then */
        assertThat(products).hasSize(2)
            .extracting("productNumber", "name", "sellingStatus")
            .containsExactlyInAnyOrder(
                tuple("001", "아메리카노", ProductSellingType.SELLING),
                tuple("002", "카페라떼", ProductSellingType.HOLD)
            )
    }

    @DisplayName("상품 번호 리스트로 상품들을 조회한다.")
    @Test
    fun findAllByProductNumberIn() {
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
        val product3 = Product(
            productNumber = "003",
            productType = ProductType.HANDMADE,
            sellingStatus = ProductSellingType.STOP_SELLING,
            name = "팥빙수",
            price = 7000,
        )
        productRepository.saveAll(listOf(product1, product2, product3))

        val productNumbers = listOf("001", "002", "003")

        /* when */
        val products = productRepository.findAllByProductNumberIn(productNumbers)

        /* then */
        assertThat(products).hasSize(3)
            .extracting("productNumber", "name", "price")
            .containsExactlyInAnyOrder(
                tuple("001", "아메리카노", 4000),
                tuple("002", "카페라떼", 4500),
                tuple("003", "팥빙수", 7000),
            )
    }

}