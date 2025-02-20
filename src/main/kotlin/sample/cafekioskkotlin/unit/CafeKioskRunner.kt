package sample.cafekioskkotlin.unit

import sample.cafekioskkotlin.unit.beverage.Americano
import sample.cafekioskkotlin.unit.beverage.Latte

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

fun main(args: Array<String>) {
    val cafeKiosk = CafeKiosk()
    cafeKiosk.add(Americano())
    println(">>> add Americano")

    cafeKiosk.add(Latte())
    println(">>> add Latte")

    val totalPrice = cafeKiosk.calculateTotal()
    println("Total Price: $totalPrice")

}