package sample.cafekioskkotlin.spring.service.order

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.mockito.kotlin.any
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.bean.override.mockito.MockitoBean
import org.springframework.transaction.annotation.Transactional
import sample.cafekioskkotlin.spring.client.mail.MailSendClient
import sample.cafekioskkotlin.spring.domain.order.Order
import sample.cafekioskkotlin.spring.domain.order.OrderStatus
import sample.cafekioskkotlin.spring.domain.product.Product
import sample.cafekioskkotlin.spring.domain.product.ProductSellingType
import sample.cafekioskkotlin.spring.domain.product.ProductType
import sample.cafekioskkotlin.spring.repository.history.mail.MailSendRepository
import sample.cafekioskkotlin.spring.repository.order.OrderRepository
import sample.cafekioskkotlin.spring.repository.product.ProductRepository
import java.time.LocalDate
import java.time.LocalDateTime

/**
 *packageName    : sample.cafekioskkotlin.spring.service.order
 * fileName       : OrderStatisticsServiceTest
 * author         : Yeong-Huns
 * date           : 2025-03-04
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2025-03-04        Yeong-Huns       최초 생성
 */
@Transactional
@ActiveProfiles("test")
@SpringBootTest
class OrderStatisticsServiceTest(
    @Autowired private val orderStatisticsService: OrderStatisticsService,
    @Autowired private val orderRepository: OrderRepository,
    @Autowired private val productRepository: ProductRepository,
    @Autowired private val mailSendRepository: MailSendRepository,
) {
    @MockitoBean
    private lateinit var mailSendClient: MailSendClient

    @DisplayName("결제완료 주문들을 조회하여 매출 통계 메일을 전송한다.")
    @Test
    fun sendOrderStatisticsMail() {
        /* given */
        val product1 = createProduct(ProductType.HANDMADE, "001", 1000)
        val product2 = createProduct(ProductType.HANDMADE, "002", 3000)
        val product3 = createProduct(ProductType.HANDMADE, "003", 5000)
        val products = listOf(product1, product2, product3)
        productRepository.saveAll(products)

        val today = LocalDateTime.of(2025, 3, 5, 0, 0)
        val order1 = createPaymentCompletedOrder(LocalDateTime.of(2025, 3, 4, 23, 59, 59), products)
        val order2 = createPaymentCompletedOrder(today, products)
        val order3 = createPaymentCompletedOrder(LocalDateTime.of(2025, 3, 5, 23, 59, 59), products)
        val order4 = createPaymentCompletedOrder(LocalDateTime.of(2025, 3, 6, 0, 0), products)

        /* stubbing */
        Mockito.`when`(
            mailSendClient.sendEmail(
                fromEmail = any<String>(),
                toEmail = any<String>(),
                subject = any<String>(),
                content = any<String>()
            )
        )
            .thenReturn(true)

        /* when */
        val result = orderStatisticsService.sendOrderStatisticsMail(LocalDate.of(2025, 3, 5), "test@test.com")

        /* then */
        assertThat(result).isTrue

        val histories = mailSendRepository.findAll()
        assertThat(histories).hasSize(1).extracting("content").contains("총 매출 합계는 18000 입니다.")
    }

    private fun createPaymentCompletedOrder(today: LocalDateTime, products: List<Product>): Order {
        val order = Order(
            products = products,
            orderStatus = OrderStatus.PAYMENT_COMPLETED,
            registeredDate = today
        )
        return orderRepository.save(order)
    }

    private fun createProduct(productType: ProductType, productNumber: String, price: Int): Product {
        return Product(
            productNumber = productNumber,
            productType = productType,
            sellingStatus = ProductSellingType.SELLING,
            name = "메뉴 이름",
            price = price,
        )
    }
}