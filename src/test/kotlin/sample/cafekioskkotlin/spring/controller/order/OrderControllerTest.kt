package sample.cafekioskkotlin.spring.controller.order

import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType
import org.springframework.test.context.bean.override.mockito.MockitoBean
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultHandlers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import sample.cafekioskkotlin.spring.dto.order.request.OrderCreateRequest
import sample.cafekioskkotlin.spring.service.order.OrderService


/**
 *packageName    : sample.cafekioskkotlin.spring.controller.order
 * fileName       : OrderControllerTest
 * author         : Yeong-Huns
 * date           : 2025-02-27
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2025-02-27        Yeong-Huns       최초 생성
 */
@WebMvcTest(controllers = [OrderController::class])
class OrderControllerTest {
    @Autowired
    private lateinit var mockMvc: MockMvc

    @Autowired
    private lateinit var mapper: ObjectMapper

    @MockitoBean
    private lateinit var orderService: OrderService

    @DisplayName("신규 주문을 등록한다.")
    @Test
    fun test1() {
        /* given */
        val request = OrderCreateRequest(listOf("001"))

        /* when */ /* then */
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/orders/new")
            .content(mapper.writeValueAsString(request)
            )
            .contentType(MediaType.APPLICATION_JSON))
            .andDo (MockMvcResultHandlers.print())
            .andExpect (MockMvcResultMatchers.status().isCreated)
    }

    @DisplayName("신규 주문을 등록할 때 상품번호는 1개 이상이여야 한다.")
    @Test
    fun test2() {
        /* given */
        val request = OrderCreateRequest(listOf())

        /* when */ /* then */
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/orders/new")
            .content(mapper.writeValueAsString(request)
            )
            .contentType(MediaType.APPLICATION_JSON))
            .andDo (MockMvcResultHandlers.print())
            .andExpect (MockMvcResultMatchers.status().isBadRequest)
            .andExpect (MockMvcResultMatchers.jsonPath("$.message").value("must not be empty"))
    }
}