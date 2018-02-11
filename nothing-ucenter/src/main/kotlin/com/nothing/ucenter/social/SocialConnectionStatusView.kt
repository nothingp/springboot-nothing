package com.nothing.ucenter.social

import com.fasterxml.jackson.databind.ObjectMapper
import org.apache.commons.collections.CollectionUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.social.connect.Connection
import org.springframework.stereotype.Component
import org.springframework.web.servlet.view.AbstractView
import java.util.HashMap
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse


@Component("connect/status")
class SocialConnectionStatusView : AbstractView() {

    @Autowired
    private val objectMapper: ObjectMapper? = null

    @Throws(Exception::class)
    override fun renderMergedOutputModel(model: Map<String, Any>, request: HttpServletRequest, response: HttpServletResponse) {
        val connections = model["connectionMap"] as Map<String, List<Connection<*>>>

        val result = HashMap<String, Boolean>()
        for (key in connections.keys) {
            result.put(key, CollectionUtils.isNotEmpty(connections[key]))
        }

        response.contentType = "application/json;charset=UTF-8"
        //response.writer.write(objectMapper!!.writeValueAsString(ResultUtil.success(result)))
    }
}