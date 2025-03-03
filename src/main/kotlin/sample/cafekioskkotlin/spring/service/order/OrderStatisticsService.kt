package sample.cafekioskkotlin.spring.service.order

import org.springframework.stereotype.Service
import sample.cafekioskkotlin.spring.domain.order.Order
import sample.cafekioskkotlin.spring.domain.order.OrderStatus
import sample.cafekioskkotlin.spring.repository.order.OrderRepository
import sample.cafekioskkotlin.spring.service.mail.MailService
import java.time.LocalDate

/**
 *packageName    : sample.cafekioskkotlin.spring.service.order
 * fileName       : OrderStatisticsService
 * author         : Yeong-Huns
 * date           : 2025-03-03
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2025-03-03        Yeong-Huns       최초 생성
 */
@Service
class OrderStatisticsService(
    private val mailService: MailService,
    private val orderRepository: OrderRepository,
) {
    fun sendOrderStatisticsMail(orderDate: LocalDate, email: String) : Boolean{
        /* 해당 일자에 결제완료된 주문들을 가져와서 , 총 매출 합계를 계산하고 메일 정송 */
        val totalAmount = orderRepository.findOrdersBy(
            orderDate.atStartOfDay(),
            orderDate.plusDays(1).atStartOfDay(),
            OrderStatus.PAYMENT_COMPLETED
        ).asSequence().map(Order::totalPrice).sum()

        val result =
            mailService.sendMail("no-reply@cafekioskkotlin.com", email, "[매출통계] $orderDate", "총 매출 합계는 $totalAmount 입니다.")
        if(!result){
            throw IllegalArgumentException("매출 통계 메일 전송에 실패했습니다.")
        }

        return true
    }
}