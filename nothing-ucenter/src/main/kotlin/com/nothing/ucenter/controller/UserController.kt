package com.nothing.ucenter.controller

import com.nothing.ucenter.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.cloud.netflix.feign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.security.Principal


@RestController
class UserController {

    @Autowired
    val zipkinClientService1: ZipkinClientService1Interface? = null

    @GetMapping("/user")
    fun user(user: Principal): Principal {
        return user
    }

    @GetMapping("/demo")
    fun getDemo(): String {
        return zipkinClientService1!!.getDemo();
    }

    @FeignClient("backend")
    interface ZipkinClientService1Interface {
        @RequestMapping("/")
        fun getDemo(): String
    }
}