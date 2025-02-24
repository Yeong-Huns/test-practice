package sample.cafekioskkotlin.spring.domain.product


import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

/**
 *packageName    : sample.cafekioskkotlin.spring.domain.product
 * fileName       : ProductTypeTest
 * author         : Yeong-Huns
 * date           : 2025-02-24
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2025-02-24        Yeong-Huns       최초 생성
 */
class ProductTypeTest {
    @DisplayName("상품 타입이 재고 관련 타입인지를 체크한다.")
    @Test
    fun containsStockType() {
        /* given */
        val productType = ProductType.HANDMADE

        /* when */
        val result = ProductType.containsStockType(productType)

        /* then */
        assertThat(result).isFalse()
    }

    @DisplayName("상품 타입이 재고 관련 타입인지를 체크한다.")
    @Test
    fun containsStockType2() {
        /* given */
        val productType = ProductType.BAKERY

        /* when */
        val result = ProductType.containsStockType(productType)

        /* then */
        assertThat(result).isTrue()
    }
}