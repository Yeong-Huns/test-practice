package sample.cafekioskkotlin.spring.controller.product

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController
import sample.cafekioskkotlin.spring.dto.product.request.ProductCreateRequest
import sample.cafekioskkotlin.spring.dto.product.response.ProductResponse
import sample.cafekioskkotlin.spring.service.order.OrderService
import sample.cafekioskkotlin.spring.service.product.ProductService

/**
 *packageName    : sample.cafekioskkotlin.spring.controller
 * fileName       : ProductController
 * author         : Yeong-Huns
 * date           : 2025-02-23
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2025-02-23        Yeong-Huns       최초 생성
 */
@RestController
class ProductController (
    private val productService: ProductService,
    private val orderService: OrderService,
){
    @GetMapping("/api/v1/products/selling")
    fun getSellingProducts() : ResponseEntity<List<ProductResponse>> = ResponseEntity.ok(productService.getSellingProduct())

    @PostMapping("/api/v1/products/new")
    fun createProduct(request: ProductCreateRequest) = ResponseEntity.ok()
}