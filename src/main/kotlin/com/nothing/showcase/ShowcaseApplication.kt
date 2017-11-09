package com.nothing.showcase

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ShowcaseApplication

fun main(args: Array<String>) {
    runApplication<ShowcaseApplication>(*args)
}
