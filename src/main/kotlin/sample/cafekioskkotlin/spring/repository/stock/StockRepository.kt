package sample.cafekioskkotlin.spring.repository.stock

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import sample.cafekioskkotlin.spring.domain.stock.Stock

/**
 *packageName    : sample.cafekioskkotlin.spring.repository.stock
 * fileName       : StockRepository
 * author         : Yeong-Huns
 * date           : 2025-02-24
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2025-02-24        Yeong-Huns       최초 생성
 */
@Repository
interface StockRepository : JpaRepository<Stock, Long> {
    fun findAllByProductNumberIn(productNumbers: List<String>): List<Stock>
}