package com.nothing.ucenter.model

import java.io.Serializable
import java.util.*
import javax.persistence.*


@MappedSuperclass
abstract class BaseModel : Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "Id")
    var id: Long? = null

    @Column(name = "CreateTime")
    var createTime: Date? = null

    @Column(name = "UpdateTime")
    var updateTime: Date? = null
}