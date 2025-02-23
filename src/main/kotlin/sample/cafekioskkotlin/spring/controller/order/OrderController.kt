package sample.cafekioskkotlin.spring.controller.order

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import sample.cafekioskkotlin.spring.dto.order.request.OrderCreateRequest
import sample.cafekioskkotlin.spring.dto.order.response.OrderResponse
import sample.cafekioskkotlin.spring.service.order.OrderService
import java.time.LocalDateTime

/**
 *packageName    : sample.cafekioskkotlin.spring.controller.order
 * fileName       : OrderController
 * author         : Yeong-Huns
 * date           : 2025-02-23
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2025-02-23        Yeong-Huns       최초 생성
 */
@RestController
class OrderController (
    private val orderService: OrderService
){
    @PostMapping("/api/v1/orders/new")
    fun createOrder(@RequestBody request : OrderCreateRequest) : ResponseEntity<OrderResponse> {
        val registeredDate = LocalDateTime.now()
        return ResponseEntity.ok(orderService.createOrder(request, registeredDate))
    }
}