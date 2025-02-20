package sample.cafekioskkotlin.unit.beverage

/**
 *packageName    : sample.cafekioskkotlin.unit
 * fileName       : Latte
 * author         : Yeong-Huns
 * date           : 2025-02-21
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2025-02-21        Yeong-Huns       최초 생성
 */
class Latte : Beverage {
    override fun getName(): String = "Latte"

    override fun getPrice(): Int = 4500
}