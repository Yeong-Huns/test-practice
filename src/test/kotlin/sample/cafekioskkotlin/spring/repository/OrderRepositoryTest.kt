package sample.cafekioskkotlin.spring.repository

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.tuple
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.transaction.annotation.Transactional
import sample.cafekioskkotlin.spring.domain.order.Order
import sample.cafekioskkotlin.spring.domain.order.OrderStatus
import sample.cafekioskkotlin.spring.domain.product.Product
import sample.cafekioskkotlin.spring.domain.product.ProductSellingType
import sample.cafekioskkotlin.spring.domain.product.ProductType
import sample.cafekioskkotlin.spring.repository.order.OrderRepository
import sample.cafekioskkotlin.spring.repository.product.ProductRepository
import java.time.LocalDateTime

/**
 *packageName    : sample.cafekioskkotlin.spring.repository
 * fileName       : OrderRepositoryTest
 * author         : Yeong-Huns
 * date           : 2025-03-03
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2025-03-03        Yeong-Huns       최초 생성
 */
@Transactional
@ActiveProfiles("test")
@DataJpaTest
class OrderRepositoryTest (
    @Autowired val orderRepository: OrderRepository,
    @Autowired val productRepository: ProductRepository,
){
    @DisplayName("주어진 범위 내에 등록된 주문들을 조회할 수 있다.")
    @Test
    fun findOrdersByTest() {
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
        val orderDateTime = LocalDateTime.of(2025, 3, 1, 10, 0)
        val orderList1 = Order.create(listOf(product1), orderDateTime)
        val orderList2 = Order.create(listOf(product1, product2), orderDateTime.plusDays(1))
        val orderList3 = Order.create(listOf(product1, product2, product3), orderDateTime.plusDays(2))
        orderRepository.saveAll(listOf(orderList1, orderList2, orderList3))

        val startDateTime : LocalDateTime = LocalDateTime.of(2025, 3, 1, 10, 0)
        val endDateTime : LocalDateTime = LocalDateTime.of(2025, 3, 3, 10, 0)
        val orderStatus : OrderStatus = OrderStatus.INIT

        /* when */
        val results = orderRepository.findOrdersBy(startDateTime, endDateTime, orderStatus)

        /* then */
        assertThat(results).hasSize(2)
            .extracting("orderStatus", "totalPrice", "registeredDateTime")
            .containsExactlyInAnyOrder(
                tuple(OrderStatus.INIT, 4000, orderDateTime),
                tuple(OrderStatus.INIT, 8500, orderDateTime.plusDays(1)),
            )
    }

}