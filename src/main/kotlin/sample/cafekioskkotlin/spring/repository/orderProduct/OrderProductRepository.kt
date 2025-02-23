package sample.cafekioskkotlin.spring.repository.orderProduct

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import sample.cafekioskkotlin.spring.domain.orderProduct.OrderProduct

/**
 *packageName    : sample.cafekioskkotlin.spring.repository.orderProduct
 * fileName       : OrderProductRepository
 * author         : Yeong-Huns
 * date           : 2025-02-23
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2025-02-23        Yeong-Huns       최초 생성
 */
@Repository
interface OrderProductRepository : JpaRepository<OrderProduct, Long>{
}