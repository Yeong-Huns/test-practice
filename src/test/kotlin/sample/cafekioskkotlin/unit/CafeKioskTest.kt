package sample.cafekioskkotlin.unit

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import sample.cafekioskkotlin.unit.beverage.Americano

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
        assertThat(cafeKiosk.beverages.count()).isEqualTo(1)
        assertThat(cafeKiosk.beverages[0].getName() == "Americano").isTrue();
    }
}