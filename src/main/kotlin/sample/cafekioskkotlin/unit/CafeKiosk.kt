package sample.cafekioskkotlin.unit

import sample.cafekioskkotlin.unit.beverage.Beverage
import sample.cafekioskkotlin.unit.order.Order
import java.time.LocalDateTime
import java.time.LocalTime

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

    companion object {
        private val SHOP_OPEN_TIME : LocalTime = LocalTime.of(10, 0)
        private val SHOP_CLOSE_TIME : LocalTime = LocalTime.of(22, 0)
    }

    var beverages = mutableListOf<Beverage>()

    fun add(beverage: Beverage) = beverages.add(beverage)

    fun add(beverage: Beverage, count: Int) {
        if(count <= 0) {
            throw IllegalArgumentException("Count must be greater than zero.")
        }
        (1.. count).forEach{ beverages.add(beverage) }
    }

    fun remove(beverage: Beverage) = beverages.remove(beverage)

    fun clear() = beverages.clear()

    fun calculateTotal(): Int = beverages.sumOf(Beverage::getPrice)

    fun createOrder(): Order{
        val currentDateTime = LocalDateTime.now()
        val currentTime = currentDateTime.toLocalTime()
        if(currentTime < SHOP_OPEN_TIME || currentTime > SHOP_CLOSE_TIME) {
            throw IllegalArgumentException("Time must be in the range of shopping time.")
        }
        return Order(currentDateTime, beverages);
    }

    fun createOrder(currentDateTime: LocalDateTime): Order{
        val currentTime = currentDateTime.toLocalTime()
        if(currentTime < SHOP_OPEN_TIME || currentTime > SHOP_CLOSE_TIME) {
            throw IllegalArgumentException("Time must be in the range of shopping time.")
        }
        return Order(currentDateTime, beverages);
    }
}