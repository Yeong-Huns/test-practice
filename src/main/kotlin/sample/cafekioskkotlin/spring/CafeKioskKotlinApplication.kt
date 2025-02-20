package sample.cafekioskkotlin.spring

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class CafeKioskKotlinApplication

fun main(args: Array<String>) {
    runApplication<CafeKioskKotlinApplication>(*args)
}
