package sample.cafekioskkotlin.spring.dto.order.request

import jakarta.validation.constraints.NotEmpty

/**
 *packageName    : sample.cafekioskkotlin.spring.dto.order.request
 * fileName       : OrderCreateRequest
 * author         : Yeong-Huns
 * date           : 2025-02-23
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2025-02-23        Yeong-Huns       최초 생성
 */
data class OrderCreateRequest(
    @field:NotEmpty
    val productNumbers : List<String>
)
