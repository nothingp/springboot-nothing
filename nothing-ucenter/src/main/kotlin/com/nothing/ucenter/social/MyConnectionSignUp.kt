package com.nothing.ucenter.social

import org.springframework.social.connect.Connection
import org.springframework.social.connect.ConnectionSignUp
import org.springframework.stereotype.Component


@Component
class MyConnectionSignUp : ConnectionSignUp {
    override fun execute(connection: Connection<*>): String {
        //根据社交用户信息，默认创建用户并返回用户唯一标识
        return connection.displayName
    }
}