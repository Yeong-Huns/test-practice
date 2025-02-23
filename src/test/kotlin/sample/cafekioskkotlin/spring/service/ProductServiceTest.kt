package sample.cafekioskkotlin.spring.service

import org.junit.jupiter.api.Assertions.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import sample.cafekioskkotlin.spring.repository.product.ProductRepository

/**
 *packageName    : sample.cafekioskkotlin.spring.service
 * fileName       : ProductServiceTest
 * author         : Yeong-Huns
 * date           : 2025-02-23
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2025-02-23        Yeong-Huns       최초 생성
 */
@ActiveProfiles("test")
@SpringBootTest
class ProductServiceTest (
    @Autowired private val repository: ProductRepository
){

}