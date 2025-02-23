package sample.cafekioskkotlin.spring.domain.common

import jakarta.persistence.EntityListeners
import jakarta.persistence.MappedSuperclass
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime

/**
 *packageName    : sample.cafekioskkotlin.spring.domain.common
 * fileName       : BaseEntity
 * author         : Yeong-Huns
 * date           : 2025-02-22
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2025-02-22        Yeong-Huns       최초 생성
 */
@MappedSuperclass
@EntityListeners(AuditingEntityListener::class)
abstract class BaseEntity (
    @CreatedDate
    var createdDateTime: LocalDateTime? = null,

    @LastModifiedDate
    var modifiedDateTime: LocalDateTime? = null,
)