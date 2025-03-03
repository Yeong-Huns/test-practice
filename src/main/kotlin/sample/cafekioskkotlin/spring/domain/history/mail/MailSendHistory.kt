package sample.cafekioskkotlin.spring.domain.history.mail

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import sample.cafekioskkotlin.spring.domain.common.BaseEntity

/**
 *packageName    : sample.cafekioskkotlin.spring.domain.history.mail
 * fileName       : MailSendHistory
 * author         : Yeong-Huns
 * date           : 2025-03-04
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2025-03-04        Yeong-Huns       최초 생성
 */
@Entity
class MailSendHistory (
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    val fromEmail: String,
    val toEmail: String,
    val subject: String,
    val content: String
): BaseEntity()