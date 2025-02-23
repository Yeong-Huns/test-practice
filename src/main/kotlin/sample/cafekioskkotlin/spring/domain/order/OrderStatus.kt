package sample.cafekioskkotlin.spring.domain.order

/**
 *packageName    : sample.cafekioskkotlin.spring.domain.order
 * fileName       : OrderStatus
 * author         : Yeong-Huns
 * date           : 2025-02-23
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2025-02-23        Yeong-Huns       최초 생성
 */
enum class OrderStatus(val text: String) {
    INIT("주문생성"),
    CANCELLED("주문취소"),
    PAYMENT_COMPLETED("결제완료"),
    PAYMENT_FAILED("결제실패"),
    RECEIVED("주문접수"),
    COMPLETED("처리완료"),
}