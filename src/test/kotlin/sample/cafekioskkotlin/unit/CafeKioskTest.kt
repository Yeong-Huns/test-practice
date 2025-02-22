package sample.cafekioskkotlin.unit

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import sample.cafekioskkotlin.unit.beverage.Americano
import sample.cafekioskkotlin.unit.beverage.Latte
import java.time.LocalDateTime

/**
 *packageName    : sample.cafekioskkotlin.unit
 * fileName       : CafeKioskTest
 * author         : Yeong-Huns
 * date           : 2025-02-21
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2025-02-21        Yeong-Huns       최초 생성
 */
class CafeKioskTest {

    @Test
    @DisplayName("카페 키오스크에 아메리카노를 추가한다.")
    fun add(){
        /* given */
        val cafeKiosk = CafeKiosk()

        /* when */
        cafeKiosk.add(Americano())

        /* then */
        assertThat(cafeKiosk.beverages).hasSize(1)
        assertThat(cafeKiosk.beverages[0].getName() == "Americano").isTrue();
    }

    @Test
    /* @DisplayName("음료 1개 추가 테스트 ") */
    @DisplayName("음료 1개를 추가하면 주문 목록에 담긴다.")
    fun addSeveralBeverages(){
        /* given */
        val cafeKiosk = CafeKiosk()
        val americano = Americano()

        /* when */
        cafeKiosk.add(americano, 2)

        /* then */
        assertThat(cafeKiosk.beverages).hasSize(2)
        assertThat(cafeKiosk.beverages[0]).isEqualTo(americano)
        assertThat(cafeKiosk.beverages[1]).isEqualTo(americano)
    }

    @Test
    @DisplayName("음료를 1개 아래로 추가하면 예외를 던진다.")
    fun addZeroBeverages(){
        /* given */
        val cafeKiosk = CafeKiosk()
        val americano = Americano()

        /* when */ /* then */
        assertThatThrownBy { cafeKiosk.add(americano, 0) }
            .isInstanceOf(IllegalArgumentException::class.java)
            .hasMessage("Count must be greater than zero.")
    }


    @Test
    @DisplayName("키오스크의 음료 목록에서 특정 음료를 제거한다.")
    fun remove(){
        val cafeKiosk = CafeKiosk()
        val americano = Americano()

        cafeKiosk.add(americano)
        cafeKiosk.remove(americano)

        assertThat(cafeKiosk.beverages).isEmpty()
    }

    @Test
    @DisplayName("키오스크의 음료목록을 초기화 한다.")
    fun clear(){
        val cafeKiosk = CafeKiosk()
        val americano = Americano()
        val latte = Latte()

        cafeKiosk.add(latte)
        cafeKiosk.add(americano)
        cafeKiosk.clear()

        assertThat(cafeKiosk.beverages).isEmpty()
    }

    @Test
    @DisplayName("주문 목록에 담긴 상품들의 총 금액을 계산할 수 있다.")
    fun calculateTotalPrice(){
        /* given */
        val cafeKiosk = CafeKiosk()
        val americano = Americano()
        val latte = Latte()

        /* when */
        cafeKiosk.add(latte)
        cafeKiosk.add(americano)
        val totalPrice = cafeKiosk.calculateTotal()

        /* then */
        assertThat(totalPrice).isEqualTo(8500)
    }

    @Test
    @DisplayName("영업 시간 내에 주문을 생성한다.")
    fun createOrder(){
        /* given */
        val cafeKiosk = CafeKiosk()
        val americano = Americano()
        val testTime = LocalDateTime.of(2025, 2, 22, 10, 0)

        /* when */
        cafeKiosk.add(americano)
        val order = cafeKiosk.createOrder(testTime)

        /* then */
        assertThat(order.beverage).hasSize(1)
        assertThat(order.beverage[0].getName()).isEqualTo("Americano")
    }

    @Test
    @DisplayName("영업시간이 아니면 주문을 생성할 수 없다. ")
    fun createOrderOutsideOpenTime(){
        /* given */
        val cafeKiosk = CafeKiosk()
        val americano = Americano()
        val testTime = LocalDateTime.of(2025, 2, 22, 23, 0)

        /* when & then */
        cafeKiosk.add(americano)
        assertThatThrownBy { cafeKiosk.createOrder(testTime) }
            .isInstanceOf(IllegalArgumentException::class.java)
            .hasMessage("Time must be in the range of shopping time.")
    }

}