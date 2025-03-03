package sample.cafekioskkotlin.spring.client.mail

import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.stereotype.Component
import sample.cafekioskkotlin.spring.service.mail.MailService

/**
 *packageName    : sample.cafekioskkotlin.spring.client.mail
 * fileName       : MailSendClient
 * author         : Yeong-Huns
 * date           : 2025-03-04
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2025-03-04        Yeong-Huns       최초 생성
 */
val logger = KotlinLogging.logger {  }
@Component
class MailSendClient {
    fun sendEmail(fromEmail: String, toEmail: String, subject: String, content: String): Boolean{
        logger.info{"메일 전송"}
        throw IllegalArgumentException("메일 전송")
        return true
    }
}