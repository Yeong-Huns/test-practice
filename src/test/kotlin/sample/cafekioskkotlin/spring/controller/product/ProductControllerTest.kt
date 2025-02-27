package sample.cafekioskkotlin.spring.controller.product

import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.mockito.Mockito.`when`
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType
import org.springframework.test.context.bean.override.mockito.MockitoBean
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultHandlers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import sample.cafekioskkotlin.spring.domain.product.ProductSellingType
import sample.cafekioskkotlin.spring.domain.product.ProductType
import sample.cafekioskkotlin.spring.dto.product.request.ProductCreateRequest
import sample.cafekioskkotlin.spring.dto.product.response.ProductResponse
import sample.cafekioskkotlin.spring.service.order.OrderService
import sample.cafekioskkotlin.spring.service.product.ProductService

/**
 *packageName    : sample.cafekioskkotlin.spring.controller.product
 * fileName       : ProductControllerTest
 * author         : Yeong-Huns
 * date           : 2025-02-26
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2025-02-26        Yeong-Huns       최초 생성
 */

@WebMvcTest(controllers = [ProductController::class])
class ProductControllerTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Autowired
    private lateinit var mapper: ObjectMapper

    @MockitoBean
    private lateinit var productService: ProductService

    @MockitoBean
    private lateinit var orderService: OrderService

    @DisplayName("신규 상품을 등록한다. ")
    @Test
    fun createProduct() {/* given */
        val productCreateRequest = ProductCreateRequest(
            type = ProductType.HANDMADE,
            sellingStatus = ProductSellingType.SELLING,
            name = "아메리카노",
            price = 4000,
        )
        mockMvc.perform(
            MockMvcRequestBuilders.post("/api/v1/products/new")/* 자바스크립트 JSON.Stringify( {object} ) 와 같음 . 객체를 string 으로 변환 */.content(mapper.writeValueAsString(productCreateRequest))/* JSON 타입임을 헤더에 명시 */
                .contentType(MediaType.APPLICATION_JSON)
        )
            /* when & then */
            .andDo { MockMvcResultHandlers.print() }
            /* 응답요청이 CREATED(201) 인가? */
            .andExpect { MockMvcResultMatchers.status().isCreated }
    }


    @DisplayName("신규 상품을 등록할 때 상품 타입은 필수값이다.")
    @Test
    fun createProductWithoutType() {/* given */
        val productCreateRequest = ProductCreateRequest(
            type = null, sellingStatus = ProductSellingType.SELLING, name = "아메리카노", price = 4000
        )
        mockMvc.perform(
            MockMvcRequestBuilders.post("/api/v1/products/new").content(mapper.writeValueAsString(productCreateRequest)).contentType(MediaType.APPLICATION_JSON)
        ).andDo(MockMvcResultHandlers.print()).andExpect(MockMvcResultMatchers.status().isBadRequest).andExpect(MockMvcResultMatchers.jsonPath("$.message").value("상품 타입은 필수입니다."))

    }

    @DisplayName("신규 상품을 등록할 때 상품 판매상태는 필수값이다.")
    @Test
    fun createProductWithoutSellingStatus() {/* given */
        val productCreateRequest = ProductCreateRequest(
            type = ProductType.HANDMADE, sellingStatus = null, name = "아메리카노", price = 4000
        )
        mockMvc.perform(
            MockMvcRequestBuilders.post("/api/v1/products/new").content(mapper.writeValueAsString(productCreateRequest)).contentType(MediaType.APPLICATION_JSON)
        ).andDo(MockMvcResultHandlers.print()).andExpect(MockMvcResultMatchers.status().isBadRequest).andExpect(MockMvcResultMatchers.jsonPath("$.message").value("상품 판매상태는 필수입니다."))

    }

    @DisplayName("신규 상품을 등록할 때 상품 이름은 공백이 될 수 없다.")
    @Test
    fun createProductWithoutNameNotBlank() {/* given */
        val productCreateRequest = ProductCreateRequest(
            type = ProductType.HANDMADE, sellingStatus = ProductSellingType.SELLING, name = "", price = 4000
        )
        mockMvc.perform(
            MockMvcRequestBuilders.post("/api/v1/products/new").content(mapper.writeValueAsString(productCreateRequest)).contentType(MediaType.APPLICATION_JSON)
        ).andDo(MockMvcResultHandlers.print()).andExpect(MockMvcResultMatchers.status().isBadRequest).andExpect(MockMvcResultMatchers.jsonPath("$.message").value("상품 이름은 필수입니다."))

    }

    @DisplayName("신규 상품을 등록할 때 상품 가격은 양수여야 한다.")
    @Test
    fun createProductPriceIsPositive() {/* given */
        val productCreateRequest = ProductCreateRequest(
            type = ProductType.HANDMADE, sellingStatus = ProductSellingType.SELLING, name = "아메리카노", price = -1000
        )
        mockMvc.perform(
            MockMvcRequestBuilders.post("/api/v1/products/new").content(mapper.writeValueAsString(productCreateRequest)).contentType(MediaType.APPLICATION_JSON)
        ).andDo(MockMvcResultHandlers.print()).andExpect(MockMvcResultMatchers.status().isBadRequest).andExpect(MockMvcResultMatchers.jsonPath("$.message").value("상품 가격은 양수여야 합니다."))

    }

    @DisplayName("판매상품을 조회한다.")
    @Test
    fun `상품_조회_01`() {
        /* given */
        val result = listOf<ProductResponse>(
            ProductResponse(
                id = 1,
                productNumber = "001",
                type = ProductType.HANDMADE,
                sellingStatus = ProductSellingType.SELLING,
                name = "아메리카노",
                price = 4000
            )
        )
        `when`(productService.getSellingProduct()).thenReturn(result)

        /* when & then */
        // when & then
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/products/selling"))
            .andDo(MockMvcResultHandlers.print())
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(1))
            .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(1))
            .andExpect(MockMvcResultMatchers.jsonPath("$[0].productNumber").value("001"))
            .andExpect(MockMvcResultMatchers.jsonPath("$[0].type").value("HANDMADE"))
            .andExpect(MockMvcResultMatchers.jsonPath("$[0].sellingStatus").value("SELLING"))
            .andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value("아메리카노"))
            .andExpect(MockMvcResultMatchers.jsonPath("$[0].price").value(4000))

    }

}