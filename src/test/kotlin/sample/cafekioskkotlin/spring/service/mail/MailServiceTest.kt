package sample.cafekioskkotlin.spring.service.mail

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.ArgumentMatchers.any
import org.mockito.ArgumentMatchers.anyString
import org.mockito.BDDMockito
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.doReturn
import org.mockito.Mockito.`when`
import org.mockito.Spy
import org.mockito.junit.jupiter.MockitoExtension

import sample.cafekioskkotlin.spring.client.mail.MailSendClient
import sample.cafekioskkotlin.spring.domain.history.mail.MailSendHistory
import sample.cafekioskkotlin.spring.repository.history.mail.MailSendRepository

/**
 *packageName    : sample.cafekioskkotlin.spring.service.mail
 * fileName       : MailServiceTest
 * author         : Yeong-Huns
 * date           : 2025-03-05
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2025-03-05        Yeong-Huns       최초 생성
 */
@ExtendWith(MockitoExtension::class)
class MailServiceTest {

    @Mock
    private lateinit var mailSendRepository: MailSendRepository

    @Mock
    private lateinit var mailSendClient: MailSendClient

    /*@Spy
    private lateinit var mailSendClient: MailSendClient*/

    @InjectMocks
    private lateinit var mailService: MailService

    @DisplayName("메일 전송 테스트")
    @Test
    fun `plain_형태`() {
        /* given */
        val mailSendClient = Mockito.mock(MailSendClient::class.java)
        val mailSendRepository = Mockito.mock(MailSendRepository::class.java)

        val mailService = MailService(mailSendClient, mailSendRepository);

        `when`(mailSendClient.sendEmail(anyString(), anyString(), anyString(), anyString()))
            .thenReturn(true);

        /* when */
        val result = mailService.sendMail("","","","")

        /* then */
        assertThat(result).isTrue
        Mockito.verify(mailSendRepository, Mockito.times(1)).save(any(MailSendHistory::class.java))
    }

    @DisplayName("메일 전송 테스트2")
    @Test
    fun `위와_동일하지만_어노테이션_기반`() {
        /* given */
        /* @InjectMocks 어노테이션이 DI(Dependency Injection) 수행 */
        /*val mailService = MailService(mailSendClient, mailSendRepository);*/
        /*
        `when`(mailSendClient.sendEmail(anyString(), anyString(), anyString(), anyString()))
            .thenReturn(true);*/
        /* Spy 는 when 문법을 사용하지 않는다. */
        /*     doReturn(true)
                 .`when`(mailSendClient)
                 .sendEmail(anyString(), anyString(), anyString(), anyString())*/
        /* BDD 방식 */
        BDDMockito.given(mailSendClient.sendEmail(anyString(), anyString(), anyString(), anyString())).willReturn(true);
        /* when */
        val result = mailService.sendMail("","","","")

        /* then */
        assertThat(result).isTrue
        Mockito.verify(mailSendRepository, Mockito.times(1)).save(any(MailSendHistory::class.java))
    }

}