package sample.cafekioskkotlin.unit.order

import sample.cafekioskkotlin.unit.beverage.Beverage
import java.time.LocalDateTime

/**
 *packageName    : sample.cafekioskkotlin.unit.order
 * fileName       : Order
 * author         : Yeong-Huns
 * date           : 2025-02-21
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2025-02-21        Yeong-Huns       최초 생성
 */
data class Order (
    private val orderDateTime: LocalDateTime,
    private val beverage: List<Beverage>,
)