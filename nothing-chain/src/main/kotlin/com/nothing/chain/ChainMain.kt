package com.nothing.chain

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import zipkin.server.EnableZipkinServer

@SpringBootApplication
@EnableZipkinServer
class ChainMain {
    companion object {
        @Throws(Exception::class)
        @JvmStatic
        fun main(args: Array<String>) {
            SpringApplication.run(ChainMain::class.java, *args)
        }
    }


}