package sample.cafekioskkotlin.spring.domain.product

/**
 *packageName    : sample.cafekioskkotlin.spring.domain.product
 * fileName       : ProductSellingType
 * author         : Yeong-Huns
 * date           : 2025-02-22
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2025-02-22        Yeong-Huns       최초 생성
 */
enum class ProductSellingType (private val text: String){
    SELLING("판매중"),
    HOLD("판매보류"),
    STOP_SELLING("판매중지");

    companion object{
        fun forDisplay() = listOf(SELLING, HOLD)
    }
}