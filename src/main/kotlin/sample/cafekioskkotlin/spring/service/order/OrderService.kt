package sample.cafekioskkotlin.spring.service.order

import jakarta.transaction.Transactional
import org.springframework.stereotype.Service
import sample.cafekioskkotlin.spring.domain.order.Order
import sample.cafekioskkotlin.spring.domain.product.Product
import sample.cafekioskkotlin.spring.domain.product.ProductType
import sample.cafekioskkotlin.spring.domain.stock.Stock
import sample.cafekioskkotlin.spring.dto.order.request.OrderCreateRequest
import sample.cafekioskkotlin.spring.dto.order.response.OrderResponse
import sample.cafekioskkotlin.spring.repository.order.OrderRepository
import sample.cafekioskkotlin.spring.repository.product.ProductRepository
import sample.cafekioskkotlin.spring.repository.stock.StockRepository
import java.time.LocalDateTime
import kotlin.sequences.forEach

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
    private val orderRepository: OrderRepository,
    private val stockRepository: StockRepository
){
    @Transactional
    fun createOrder(request : OrderCreateRequest, registeredDate: LocalDateTime) : OrderResponse {
        val productNumbers = request.productNumbers
        val duplicateProduct = findProductBy(productNumbers)

        deductStockQuantities(duplicateProduct)

        val order = Order.create(duplicateProduct, registeredDate)
        val savedOrder = orderRepository.save(order)

        return OrderResponse.fromOrder(savedOrder)
    }

    private fun findProductBy(productNumbers: List<String>) : List<Product> {
        val products = productRepository.findAllByProductNumberIn(productNumbers)
        val productMap = products.associateBy(Product::productNumber)
        return productNumbers.map(productMap::getValue)
    }

    private fun deductStockQuantities(products: List<Product>){
        /* 재고 차감이 필요한 상품들 check */
        val stockProductNumbers = extractStockProductNumbers(products)

        /* Stock 엔티티 조회 */
        val stockMap = createStockMapBy(stockProductNumbers)

        /* 상품별 카운팅 */
        val productCountingMap = stockProductNumbers.asSequence().groupingBy {it}.eachCount()

        /* 재고 차감 */
        stockProductNumbers.asSequence().distinct().forEach {
            val stock = stockMap.getValue(it)
            val quantity = productCountingMap.getValue(it).toInt()
            if(stock.isQuantityLessThan(quantity)) {
                throw IllegalArgumentException("재고가 부족한 상품이 있습니다.")
            }
            stock.deductQuantity(quantity);
        }
    }

    private fun extractStockProductNumbers(products: List<Product>) : List<String> {
        return products.asSequence()
            .filter{ProductType.containsStockType(it.productType)}
            .map(Product::productNumber)
            .toList()
    }

    private fun createStockMapBy(stockProductNumbers: List<String>) : Map<String, Stock>{
        val stocks = stockRepository.findAllByProductNumberIn(stockProductNumbers)
        return stocks.associateBy { it.productNumber }
    }

}