package com.nothing.ucenter.social.qq.api

class QQUserInfo {
    /**
     * 返回码
     */
    val ret: String? = null
    /**
     * 如果ret<0，会有相应的错误信息提示，返回数据全部用UTF-8编码。
     */
    val msg: String? = null
    /**
     *
     */
    var openId: String? = null
    /**
     * 不知道什么东西，文档上没写，但是实际api返回里有。
     */
    val is_lost: String? = null
    /**
     * 省(直辖市)
     */
    val province: String? = null
    /**
     * 市(直辖市区)
     */
    val city: String? = null
    /**
     * 出生年月
     */
    val year: String? = null
    /**
     * 用户在QQ空间的昵称。
     */
    val nickname: String? = null
    /**
     * 大小为30×30像素的QQ空间头像URL。
     */
    val figureurl: String? = null
    /**
     * 大小为50×50像素的QQ空间头像URL。
     */
    val figureurl_1: String? = null
    /**
     * 大小为100×100像素的QQ空间头像URL。
     */
    val figureurl_2: String? = null
    /**
     * 大小为40×40像素的QQ头像URL。
     */
    val figureurl_qq_1: String? = null
    /**
     * 大小为100×100像素的QQ头像URL。需要注意，不是所有的用户都拥有QQ的100×100的头像，但40×40像素则是一定会有。
     */
    val figureurl_qq_2: String? = null
    /**
     * 性别。 如果获取不到则默认返回”男”
     */
    val gender: String? = null
    /**
     * 标识用户是否为黄钻用户（0：不是；1：是）。
     */
    val is_yellow_vip: String? = null
    /**
     * 标识用户是否为黄钻用户（0：不是；1：是）
     */
    val vip: String? = null
    /**
     * 黄钻等级
     */
    val yellow_vip_level: String? = null
    /**
     * 黄钻等级
     */
    val level: String? = null
    /**
     * 标识是否为年费黄钻用户（0：不是； 1：是）
     */
    val is_yellow_year_vip: String? = null
}