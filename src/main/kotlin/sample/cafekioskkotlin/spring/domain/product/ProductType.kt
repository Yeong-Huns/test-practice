package sample.cafekioskkotlin.spring.domain.product

/**
 *packageName    : sample.cafekioskkotlin.spring.domain.product
 * fileName       : ProductType
 * author         : Yeong-Huns
 * date           : 2025-02-22
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2025-02-22        Yeong-Huns       최초 생성
 */
enum class ProductType (private val text: String){
    HANDMADE("제조 음료"),
    BOTTLE("병 음료"),
    BAKERY("베이커리"),
}