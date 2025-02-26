package sample.cafekioskkotlin.spring.global.error.handler

import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.http.ResponseEntity
import org.springframework.validation.BindException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import sample.cafekioskkotlin.spring.global.error.errorCode.ErrorCode
import sample.cafekioskkotlin.spring.global.error.response.ErrorResponse

/**
 *packageName    : sample.cafekioskkotlin.spring.global.error.handler
 * fileName       : GlobalExceptionHandler
 * author         : Yeong-Huns
 * date           : 2025-02-27
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2025-02-27        Yeong-Huns       최초 생성
 */

val logger = KotlinLogging.logger {  }

@RestControllerAdvice
class GlobalExceptionHandler {
    @ExceptionHandler(BindException::class)
    fun handle(bindException: BindException): ResponseEntity<ErrorResponse> {
        logger.error { "[BindException]: ${bindException.objectName} [Details] ${bindException.bindingResult.allErrors[0].defaultMessage!!}" }
        return createErrorResponse(ErrorCode.NOT_VALID_DATA, bindException.bindingResult.allErrors[0].defaultMessage!! )
    }


    private fun createErrorResponse(errorCode: ErrorCode) : ResponseEntity<ErrorResponse> {
        return ResponseEntity<ErrorResponse>(ErrorResponse.from(errorCode),errorCode.httpStatus)
    }

    private fun createErrorResponse(errorCode: ErrorCode, message: String) : ResponseEntity<ErrorResponse> {
        return ResponseEntity<ErrorResponse>(ErrorResponse(message), errorCode.httpStatus)
    }
}