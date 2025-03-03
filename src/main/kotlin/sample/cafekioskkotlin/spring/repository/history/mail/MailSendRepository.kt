package sample.cafekioskkotlin.spring.repository.history.mail

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import sample.cafekioskkotlin.spring.domain.history.mail.MailSendHistory

/**
 *packageName    : sample.cafekioskkotlin.spring.repository.history.mail
 * fileName       : MailSendRepository
 * author         : Yeong-Huns
 * date           : 2025-03-04
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2025-03-04        Yeong-Huns       최초 생성
 */
@Repository
interface MailSendRepository : JpaRepository<MailSendHistory, Long>