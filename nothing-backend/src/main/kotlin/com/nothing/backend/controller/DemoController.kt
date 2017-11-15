package com.nothing.backend.controller

import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import java.security.Principal


@RestController
class DemoController {

    @GetMapping("/user")
    fun user(user: Principal): Principal {
        return user
    }


    @GetMapping("/demo")
    fun getDemo(): String {
        return "good"
    }
}