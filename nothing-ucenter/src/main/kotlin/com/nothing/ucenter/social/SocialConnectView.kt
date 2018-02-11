package com.nothing.ucenter.social

import org.springframework.web.servlet.view.AbstractView
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse


class SocialConnectView : AbstractView() {
    @Throws(Exception::class)
    override fun renderMergedOutputModel(model: Map<String, Any>, request: HttpServletRequest, response: HttpServletResponse) {
        var msg = ""
        response.contentType = "text/html;charset=UTF-8"
        if (model["connections"] == null) {
            msg = "unBindingSuccess"
            //            response.getWriter().write("<h3>解绑成功</h3>");
        } else {
            msg = "bindingSuccess"
            //            response.getWriter().write("<h3>绑定成功</h3>");
        }

        response.sendRedirect("/message/" + msg)
    }
}