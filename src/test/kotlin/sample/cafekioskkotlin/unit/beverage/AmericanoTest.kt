package sample.cafekioskkotlin.unit.beverage


import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

/**
 *packageName    : sample.cafekioskkotlin.unit.beverage
 * fileName       : AmericanoTest
 * author         : Yeong-Huns
 * date           : 2025-02-22
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2025-02-22        Yeong-Huns       최초 생성
 */
class AmericanoTest {

    @Test
    fun getName(){
        /* given */
        val americano = Americano()

        /* when & then */
        assertThat(americano.getName()).isEqualTo("Americano")
    }

    @Test
    fun getPrice(){
        /* given */
        val americano = Americano()

        /* when & then */
        assertThat(americano.getPrice()).isEqualTo(4000)
    }
}