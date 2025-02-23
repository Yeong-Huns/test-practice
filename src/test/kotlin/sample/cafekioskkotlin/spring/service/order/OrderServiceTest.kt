package sample.cafekioskkotlin.spring.service.order

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.tuple
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import sample.cafekioskkotlin.spring.domain.product.Product
import sample.cafekioskkotlin.spring.domain.product.ProductSellingType
import sample.cafekioskkotlin.spring.domain.product.ProductType
import sample.cafekioskkotlin.spring.dto.order.request.OrderCreateRequest
import sample.cafekioskkotlin.spring.dto.order.response.OrderResponse
import sample.cafekioskkotlin.spring.repository.order.OrderRepository
import sample.cafekioskkotlin.spring.repository.orderProduct.OrderProductRepository
import sample.cafekioskkotlin.spring.repository.product.ProductRepository
import java.time.LocalDateTime

/**
 *packageName    : sample.cafekioskkotlin.spring.service.order
 * fileName       : OrderServiceTest
 * author         : Yeong-Huns
 * date           : 2025-02-23
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2025-02-23        Yeong-Huns       최초 생성
 */
@ActiveProfiles("test")
@SpringBootTest
class OrderServiceTest(
    @Autowired private val orderService: OrderService,
    @Autowired private val productRepository: ProductRepository,
    @Autowired private val orderProductRepository: OrderProductRepository,
    @Autowired private val orderRepository: OrderRepository
) {

    @AfterEach
    fun tearDown() {
        orderProductRepository.deleteAllInBatch()
        orderRepository.deleteAllInBatch()
        productRepository.deleteAllInBatch()
    }

    @DisplayName("주문번호 리스트를 받아 주문을 생성한다.")
    @Test
    fun createOrder() {
        /*given*/
        val registeredDate = LocalDateTime.now()
        val product1 = createProduct("001", 1000)
        val product2 = createProduct("002", 3000)
        val product3 = createProduct("003", 5000)
        productRepository.saveAll(listOf(product1, product2, product3))

        val orderCreateRequest = OrderCreateRequest(listOf("001", "002"))

        /*when*/
        val orderResponse: OrderResponse = orderService.createOrder(orderCreateRequest, registeredDate)

        /*then*/
        assertThat(orderResponse.id).isNotNull()

        assertThat(orderResponse)
            .extracting("registeredDateTime", "totalPrice")
            .contains(registeredDate, 4000)

        assertThat(orderResponse.products).hasSize(2)
            .extracting("productNumber", "price")
            .containsExactlyInAnyOrder(
                tuple("001", 1000),
                tuple("002", 3000),
            )
    }

    @DisplayName("중복되는 상품번호 리스트로 주문을 생성할 수 있다.")
    @Test
    fun createOrderWithDuplicateProductNumbers() {
        /* given */
        val registeredDate = LocalDateTime.now()
        val product1 = createProduct("001", 1000)
        val product2 = createProduct("002", 3000)
        val product3 = createProduct("003", 5000)
        productRepository.saveAll(listOf(product1, product2, product3))

        val orderCreateRequest = OrderCreateRequest(listOf("001", "001"))

        /* when */
        val orderResponse : OrderResponse = orderService.createOrder(orderCreateRequest, registeredDate)

        /* then */
        assertThat(orderResponse.id).isNotNull()

        assertThat(orderResponse)
            .extracting("registeredDateTime", "totalPrice")
            .contains(registeredDate, 2000)

        assertThat(orderResponse.products).hasSize(2)
            .extracting("productNumber", "price")
            .containsExactlyInAnyOrder(
                tuple("001", 1000),
                tuple("001", 1000),
            )
    }

    private fun createProduct(productNumber: String, price: Int): Product {
        return Product(
            productNumber = productNumber,
            productType = ProductType.HANDMADE,
            sellingStatus = ProductSellingType.SELLING,
            name = "메뉴 이름",
            price = price,
        )
    }
}