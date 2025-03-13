package sample.cafekioskkotlin.spring.domain.order

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import sample.cafekioskkotlin.spring.domain.product.Product
import sample.cafekioskkotlin.spring.domain.product.ProductSellingType
import sample.cafekioskkotlin.spring.domain.product.ProductType
import java.time.LocalDateTime

/**
 *packageName    : sample.cafekioskkotlin.spring.domain.order
 * fileName       : OrderTest
 * author         : Yeong-Huns
 * date           : 2025-02-23
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2025-02-23        Yeong-Huns       최초 생성
 */
class OrderTest {

    @DisplayName("주문 생성 시 상품 리스트에서 주문의 총 금액을 계산한다.")
    @Test
    fun calculateTotalPrice() {/* given */
        val registeredDate = LocalDateTime.now()
        val product1 = createProduct("001", 1000)
        val product2 = createProduct("002", 3000)
        val product3 = createProduct("003", 5000)

        val products = listOf(product1, product2, product3)

        /* when */
        val order = Order.create(products, registeredDate)

        /* then */
        assertThat(order.totalPrice).isEqualTo(9000)
        assertThat(order.registeredDateTime).isEqualTo(registeredDate)
    }

    @DisplayName("주문 생성 시 주문 상태는 INIT 이여야 한다. ")
    @Test
    fun init() {/* given */
        val registeredDate = LocalDateTime.now()
        val product1 = createProduct("001", 1000)
        val product2 = createProduct("002", 3000)
        val product3 = createProduct("003", 5000)

        val products = listOf(product1, product2, product3)

        /* when */
        val order = Order.create(products, registeredDate)

        /* then */
        assertThat(order.orderStatus).isEqualTo(OrderStatus.INIT)
        assertThat(order.registeredDateTime).isEqualTo(registeredDate)
    }

    @DisplayName("주문 생성 시 주문 등록 시간을 기록한다.")
    @Test
    fun registeredDateTime() {
        /* given */
        /* 해당 로직은 등록 시간에 따라서 테스트 결과가 바뀌지 않는데, 이런 경우에는 now() 를 써도 될까? */
        /* -> 그럼에도 지양해야한다. 한명이 쓰기 시작하면 전염되기 때문 + 로직이 어떻게 변할지 모름 */
        val registeredDateTime = LocalDateTime.now()
        val products = listOf(createProduct("001", 1000), createProduct("002", 2000))

        /* when */
        val order  = Order.create(products, registeredDateTime)

        /* then */
        assertThat(order.registeredDateTime).isEqualTo(registeredDateTime)
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