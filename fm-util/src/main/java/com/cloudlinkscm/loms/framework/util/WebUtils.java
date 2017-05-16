package com.cloudlinkscm.loms.framework.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * todo:: test
 * @author : tac
 * @date : 2017/5/16
 */
public class WebUtils {

    public static void writeJson(HttpServletResponse response, Object obj) throws IOException {
        String json = new ObjectMapper().writeValueAsString(obj);
        response.setStatus(HttpStatus.OK.value()); //设置状态码
        response.setContentType(MediaType.APPLICATION_JSON_VALUE); //设置ContentType
        response.setCharacterEncoding("UTF-8"); //避免乱码
        response.setHeader("Cache-Control", "no-cache, must-revalidate");
        response.getWriter().write(json);
    }


    public static boolean isAjax(HttpServletRequest request) {
        //todo::
        return request.getHeader("accept").contains("application/json");
    }
}
