package sample.cafekioskkotlin.spring.service.mail

import org.springframework.stereotype.Service
import sample.cafekioskkotlin.spring.client.mail.MailSendClient
import sample.cafekioskkotlin.spring.domain.history.mail.MailSendHistory
import sample.cafekioskkotlin.spring.repository.history.mail.MailSendRepository

/**
 *packageName    : sample.cafekioskkotlin.spring.service.mail
 * fileName       : MailService
 * author         : Yeong-Huns
 * date           : 2025-03-04
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2025-03-04        Yeong-Huns       최초 생성
 */
@Service
class MailService (
    private val mailSendClient: MailSendClient,
    private val mailSendRepository: MailSendRepository
){
    fun sendMail(fromEmail: String, toEmail: String, subject: String, content: String): Boolean {
        val result = mailSendClient.sendEmail(fromEmail, toEmail, subject, content)
        if(result){
            mailSendRepository.save(MailSendHistory(
                fromEmail = fromEmail,
                toEmail = toEmail,
                subject = subject,
                content = content
            ))
            return true
        }
        return false
    }
}