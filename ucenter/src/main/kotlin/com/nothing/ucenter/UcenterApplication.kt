package com.nothing.ucenter

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
class UcenterApplication

fun main(args: Array<String>) {
    SpringApplication.run(UcenterApplication::class.java, *args)
}
