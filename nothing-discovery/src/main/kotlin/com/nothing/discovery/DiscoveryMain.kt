package com.nothing.discovery

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer

@SpringBootApplication
@EnableEurekaServer
class DiscoveryMain{
    companion object {
        @Throws(Exception::class)
        @JvmStatic
        fun main(args: Array<String>) {
            SpringApplication.run(DiscoveryMain::class.java, *args)
        }
    }


}