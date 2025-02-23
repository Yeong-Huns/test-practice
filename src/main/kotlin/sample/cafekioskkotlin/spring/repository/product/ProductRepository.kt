package sample.cafekioskkotlin.spring.repository.product

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import sample.cafekioskkotlin.spring.domain.product.Product
import sample.cafekioskkotlin.spring.domain.product.ProductSellingType

/**
 *packageName    : sample.cafekioskkotlin.spring.repository
 * fileName       : ProductRepository
 * author         : Yeong-Huns
 * date           : 2025-02-23
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2025-02-23        Yeong-Huns       최초 생성
 */
@Repository
interface ProductRepository: JpaRepository<Product, Long> {
    /*
    * select
    * from product
    * where selling_status in ('SELLING' , 'HOLD')
    * */
    fun findAllBySellingStatusIn(sellingStatus :List<ProductSellingType>) : List<Product>

    fun findAllByProductNumberIn (productNumbers :List<String>) : List<Product>
}