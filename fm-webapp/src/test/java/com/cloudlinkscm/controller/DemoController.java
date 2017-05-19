package com.cloudlinkscm.controller;

import com.cloudlinkscm.loms.framework.core.exception.BizException;
import com.cloudlinkscm.loms.framework.core.pojo.ErrorCode;
import com.cloudlinkscm.loms.framework.core.pojo.Language;
import com.cloudlinkscm.loms.framework.core.pojo.RestfulApiResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @author : tac
 * @date : 2017/5/17
 */

@RequestMapping("/demo")
@RestController
public class DemoController {

    @RequestMapping("/index")
    public RestfulApiResponse index(){
        return RestfulApiResponse.success("操作成功", "hello world");
    }

    @RequestMapping("/sys_exception")
    public RestfulApiResponse sysException(){
        throw new NullPointerException("it's a system exception");
    }

    @RequestMapping("/biz_exception")
    public RestfulApiResponse bizException(){
        throw new BizException(new ErrorCode() {
            @Override
            public String getInternationalMessage(Language language, Object... args) {
                return "it's a business exception";
            }

            @Override
            public String getMessage() {
                return "这是一个业务异常";
            }

            @Override
            public String getInternationalMessage(Language language) {
                return "it's a business exception";
            }

            @Override
            public String getCode() {
                return "2333";
            }
        });
    }

    @RequestMapping("/date_formatter")
    public RestfulApiResponse<Date> dateFormatter(){
        return RestfulApiResponse.success("操作成功", new Date());
    }
}
