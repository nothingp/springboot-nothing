package com.nothing.monitor

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard
import org.springframework.cloud.netflix.turbine.stream.EnableTurbineStream

@SpringBootApplication
@EnableTurbineStream
@EnableHystrixDashboard
class MonitoringMain {
    companion object {
        @Throws(Exception::class)
        @JvmStatic
        fun main(args: Array<String>) {
            SpringApplication.run(MonitoringMain::class.java, *args)
        }
    }


}