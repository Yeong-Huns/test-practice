package sample.cafekioskkotlin.spring.service.order

import jakarta.transaction.Transactional
import org.springframework.stereotype.Service
import sample.cafekioskkotlin.spring.domain.order.Order
import sample.cafekioskkotlin.spring.domain.product.Product
import sample.cafekioskkotlin.spring.dto.order.request.OrderCreateRequest
import sample.cafekioskkotlin.spring.dto.order.response.OrderResponse
import sample.cafekioskkotlin.spring.repository.order.OrderRepository
import sample.cafekioskkotlin.spring.repository.product.ProductRepository
import java.time.LocalDateTime

/**
 *packageName    : sample.cafekioskkotlin.spring.service.order
 * fileName       : OrderService
 * author         : Yeong-Huns
 * date           : 2025-02-23
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2025-02-23        Yeong-Huns       최초 생성
 */
@Service
class OrderService (
    private val productRepository: ProductRepository,
    private val orderRepository: OrderRepository
){
    @Transactional
    fun createOrder(request : OrderCreateRequest, registeredDate: LocalDateTime) : OrderResponse {
        val productNumbers = request.productNumbers
        val duplicateProduct = findProductBy(productNumbers)

        val order = Order.create(duplicateProduct, registeredDate)
        val savedOrder = orderRepository.save(order)

        return OrderResponse.fromOrder(savedOrder)
    }

    private fun findProductBy(productNumbers: List<String>) : List<Product> {
        val products = productRepository.findAllByProductNumberIn(productNumbers)
        val productMap = products.associateBy(Product::productNumber)
        return productNumbers.map(productMap::getValue)
    }
}