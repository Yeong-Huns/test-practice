package sample.cafekioskkotlin.spring.repository.order

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import sample.cafekioskkotlin.spring.domain.order.Order

/**
 *packageName    : sample.cafekioskkotlin.spring.repository.order
 * fileName       : OrderRepository
 * author         : Yeong-Huns
 * date           : 2025-02-23
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2025-02-23        Yeong-Huns       최초 생성
 */
@Repository
interface OrderRepository : JpaRepository<Order, Long>{
}