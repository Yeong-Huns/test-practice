package sample.cafekioskkotlin.spring.repository

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.tuple
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.transaction.annotation.Transactional
import sample.cafekioskkotlin.spring.domain.stock.Stock
import sample.cafekioskkotlin.spring.repository.stock.StockRepository

/**
 *packageName    : sample.cafekioskkotlin.spring.repository
 * fileName       : StockRepositoryTest
 * author         : Yeong-Huns
 * date           : 2025-02-24
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2025-02-24        Yeong-Huns       최초 생성
 */
@Transactional
@ActiveProfiles("test")
@SpringBootTest
class StockRepositoryTest (
    @Autowired val stockRepository: StockRepository
){
    @DisplayName("상품번호 리스트로 재고를 조회한다.")
    @Test
    fun findAllByProductNumberIn() {
        /* given */
        val stock1 = Stock.create("001",1)
        val stock2 = Stock.create("002",2)
        val stock3 = Stock.create("003",3)
        stockRepository.saveAll(listOf(stock1, stock2, stock3))

        /* when */
        val stocks = stockRepository.findAllByProductNumberIn(listOf("001", "002"))

        /* then */
        assertThat(stocks).hasSize(2)
            .extracting("productNumber", "quantity")
            .containsExactlyInAnyOrder(
                tuple("001",1),
                tuple("002",2),
            )
    }

}