package sample.cafekioskkotlin.spring.repository

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.context.ActiveProfiles
import sample.cafekioskkotlin.spring.domain.history.mail.MailSendHistory
import sample.cafekioskkotlin.spring.repository.history.mail.MailSendRepository

/**
 *packageName    : sample.cafekioskkotlin.spring.repository
 * fileName       : MailSendRepository
 * author         : Yeong-Huns
 * date           : 2025-03-04
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2025-03-04        Yeong-Huns       최초 생성
 */
@ActiveProfiles("test")
@DataJpaTest
class MailSendRepositoryTest (
    @Autowired private val mailSendRepository: MailSendRepository
){
    @DisplayName("MailSendHistory 저장에 성공한다.")
    @Test
    fun createTest() {
        /* given */
        val fromEmail = "no-reply@test.com"
        val toEmail = "test@test.com"
        val subject = "테스트"
        val content = "테스트내용"
        val mailSendHistory = MailSendHistory(fromEmail = fromEmail, toEmail = toEmail, subject = subject, content = content)

        /* when */
        val result = mailSendRepository.save(mailSendHistory)

        /* then */
        assertThat(result)
            .extracting("id", "fromEmail", "toEmail", "subject", "content")
            .containsExactlyInAnyOrder(1L, fromEmail, toEmail, subject, content)
    }
}