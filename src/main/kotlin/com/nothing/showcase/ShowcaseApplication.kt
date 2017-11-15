package com.nothing.showcase

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
class ShowcaseApplication

fun main(args: Array<String>) {
    SpringApplication.run(ShowcaseApplication::class.java, *args)
}
