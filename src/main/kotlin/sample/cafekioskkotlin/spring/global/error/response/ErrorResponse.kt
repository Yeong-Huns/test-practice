package sample.cafekioskkotlin.spring.global.error.response

import sample.cafekioskkotlin.spring.global.error.errorCode.ErrorCode

/**
 *packageName    : sample.cafekioskkotlin.spring.global.error.response
 * fileName       : ErrorResponse
 * author         : Yeong-Huns
 * date           : 2025-02-27
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2025-02-27        Yeong-Huns       최초 생성
 */
data class ErrorResponse(
    val message: String,
){
    companion object{
        fun from(errorCode: ErrorCode): ErrorResponse {
            return ErrorResponse(errorCode.text)
        }
    }
}
