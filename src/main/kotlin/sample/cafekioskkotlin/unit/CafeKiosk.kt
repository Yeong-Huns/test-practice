package sample.cafekioskkotlin.unit

import sample.cafekioskkotlin.unit.beverage.Beverage
import sample.cafekioskkotlin.unit.order.Order
import java.time.LocalDateTime

/**
 *packageName    : sample.cafekioskkotlin.unit
 * fileName       : CafeKiosk
 * author         : Yeong-Huns
 * date           : 2025-02-21
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2025-02-21        Yeong-Huns       최초 생성
 */

class CafeKiosk{

    var beverages = mutableListOf<Beverage>()

    fun add(beverage: Beverage) = beverages.add(beverage)

    fun remove(beverage: Beverage) = beverages.remove(beverage)

    fun clear() = beverages.clear()

    fun calculateTotal(): Int = beverages.sumOf { it.getPrice() }

    fun createOrder(): Order = Order(LocalDateTime.now(), beverages);
}