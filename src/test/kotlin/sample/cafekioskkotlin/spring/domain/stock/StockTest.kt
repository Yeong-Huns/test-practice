package sample.cafekioskkotlin.spring.domain.stock

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

/**
 *packageName    : sample.cafekioskkotlin.spring.domain.stock
 * fileName       : StockTest
 * author         : Yeong-Huns
 * date           : 2025-02-24
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2025-02-24        Yeong-Huns       최초 생성
 */
class StockTest {
    @DisplayName("재고의 수량이 제공된 수량보다 작은지 확인한다.")
    @Test
    fun isQuantityLessThan() {
        /* given */
        val stock = Stock.create("001", 3)
        val quantity = 4;

        /* when */
        val result = stock.isQuantityLessThan(quantity)

        /* then */
        assertThat(result).isTrue
    }

    @DisplayName("재고의 수량이 제공된 수량보다 많은지 확인한다.")
    @Test
    fun isQuantityMoreThan() {
        /* given */
        val stock = Stock.create("001", 3)
        val quantity = 2;

        /* when */
        val result = stock.isQuantityLessThan(quantity)

        /* then */
        assertThat(result).isFalse
    }

    @DisplayName("재고를 주어진 갯수만큼 차감할 수 있다.")
    @Test
    fun deductQuantity() {
        /* given */
        val stock = Stock.create("001", 3)
        val quantity = 1;

        /* when */
        stock.deductQuantity(quantity)

        /* then */
        assertThat(stock.quantity).isEqualTo(2)
    }


    @DisplayName("재고보다 많은 수의 수량으로 차감 시도하는 경우 예외가 발생한다.")
    @Test
    fun deductQuantity2() {
        /* given */
        val stock = Stock.create("001", 2)
        val quantity = 4;

        /* when & then */
        assertThatThrownBy { stock.deductQuantity(quantity) }
            .isInstanceOf(IllegalArgumentException::class.java)
            .hasMessage("차감할 재고 수량이 없습니다.")
    }

}