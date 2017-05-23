package com.cloudlinkscm.loms.framework.webapp;

import com.cloudlinkscm.loms.framework.core.exception.BizException;
import com.cloudlinkscm.loms.framework.core.exception.BizExceptionWithArguments;
import com.cloudlinkscm.loms.framework.core.pojo.Language;
import com.cloudlinkscm.loms.framework.core.pojo.RestfulApiResponse;
import com.cloudlinkscm.loms.framework.dao.BizDataInterface;
import com.cloudlinkscm.loms.framework.dao.DummyBizDataInterface;
import com.cloudlinkscm.loms.framework.util.SpringUtils;
import com.cloudlinkscm.loms.framework.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * todo:: test
 * @author : tac
 * @date : 2017/5/16
 */
public class ExceptionHandler implements HandlerExceptionResolver {
    private boolean _debug = false;     //todo:: 后续考虑放在配置文件中控制
    private final Logger LOG = LoggerFactory.getLogger(this.getClass());

    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        RestfulApiResponse result;
        if (ex instanceof BizException) {
            if(ex instanceof BizExceptionWithArguments){
                BizExceptionWithArguments bizEx = (BizExceptionWithArguments) ex;
                result = handlerBizExceptionWithArguments(bizEx);
            }else {
                BizException bizEx = (BizException) ex;
                result = handlerBizException(bizEx);
            }
        } else {
            result = handlerSysException(ex);
        }

        try {
            WebUtils.writeJson(response, result);
        } catch (IOException e) {
            LOG.error("http响应流写入异常", e);
        }

        return new ModelAndView();      //这里要返回一个空的model and view
    }

    private RestfulApiResponse handlerSysException(Exception ex) {
        String stackTrace = _debug ? getStackTrace(ex) : "详情请查看日志";
        LOG.error("执行请求的过程中发生了未经处理的异常：" + ex.getMessage(), ex);
        return RestfulApiResponse.error(ex.getMessage(), stackTrace);
    }

    private RestfulApiResponse handlerBizException(BizException bizEx) {
        String stackTrace = _debug ? getStackTrace(bizEx) : "";
        return RestfulApiResponse.failure(
                bizEx.getErrorCode().getCode(),
                bizEx.getErrorCode().getInternationalMessage(getBizDataInterface().currentUserLanguage()),
                stackTrace);
    }

    private RestfulApiResponse handlerBizExceptionWithArguments(BizExceptionWithArguments bizEx) {
        String stackTrace = _debug ? getStackTrace(bizEx) : "";
        return RestfulApiResponse.failure(
                bizEx.getErrorCode().getCode(),
                bizEx.getErrorCode().getInternationalMessage(getBizDataInterface().currentUserLanguage(), bizEx.getArgs().toArray()),
                stackTrace);
    }

    private static String getStackTrace(Throwable e){
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw, true);
        e.printStackTrace(pw);
        pw.flush();
        sw.flush();
        return sw.toString();
    }

    //todo:: 此处代码与generic dao中的代码有重复，后续考虑如何抽象为utils
    private BizDataInterface getBizDataInterface(){
        BizDataInterface data = SpringUtils.getBean(BizDataInterface.class);
        if (data == null) {
            LOG.warn("can't find available bean of BizDataInterface");
            return DummyBizDataInterface.instance();
        }
        return data;
    }
}
