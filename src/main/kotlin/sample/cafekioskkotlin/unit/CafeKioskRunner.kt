package sample.cafekioskkotlin.unit

import sample.cafekioskkotlin.unit.beverage.Americano
import sample.cafekioskkotlin.unit.beverage.Latte
import java.time.LocalDateTime

/**
 *packageName    : sample.cafekioskkotlin.unit
 * fileName       : CafeKioskRunner
 * author         : Yeong-Huns
 * date           : 2025-02-21
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2025-02-21        Yeong-Huns       최초 생성
 */
class CafeKioskRunner

fun main() {
    val cafeKiosk = CafeKiosk()
    cafeKiosk.add(Americano())
    println(">>> add Americano")

    cafeKiosk.add(Latte())
    println(">>> add Latte")

    val totalPrice = cafeKiosk.calculateTotal()
    println("Total Price: $totalPrice")

    val order = cafeKiosk.createOrder(LocalDateTime.now())
    println(">>> Create Order ${order.beverage} Beverages")
}