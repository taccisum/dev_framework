package com.cloudlinkscm.loms.framework.webapp;

import com.cloudlinkscm.loms.framework.core.exception.BizException;
import com.cloudlinkscm.loms.framework.core.exception.BizExceptionWithArguments;
import com.cloudlinkscm.loms.framework.core.pojo.RestfulApiResponse;
import com.cloudlinkscm.loms.framework.dao.BizDataInterface;
import com.cloudlinkscm.loms.framework.util.WebUtils;
import com.cloudlinkscm.loms.framework.webapp.response.processer.adapter.ResponseAdapterFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import com.cloudlinkscm.loms.framework.core.pojo.ErrorCode;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * 全局的异常处理器
 *
 * <p>
 *     此异常处理器会对所有未处理的异常进行统一捕获处理，最终返回统一的响应格式{@link RestfulApiResponse}方便前端处理。
 * </p>
 *
 * <p>
 *     针对{@link BizException}及{@link BizExceptionWithArguments}及其派生的异常会进行特殊处理，
 *     通过异常对应的{@link ErrorCode}获取到错误信息及错误码返回给前端展示
 * </p>
 *
 * <p>
 *     todo:: 未实现
 *     对于配置为debug模式的应用，异常响应中会包含堆栈跟踪信息，而对于release下的应用，其堆栈跟踪信息则永远为空
 * </p>
 *
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
        Integer adapterType = 0;
        try {
            adapterType = Integer.parseInt(request.getParameter("adaptTo"));
        }catch (NumberFormatException ignored){}

        Object o1 = null;
        try {
            o1 = ResponseAdapterFactory.create(adapterType).doAdapt((RestfulApiResponse) result);
        }catch (Exception e){
            o1 = result;
        }

        try {
            WebUtils.writeJson(response, o1);
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
                bizEx.getErrorCode().getInternationalMessage(BizDataInterface.getBean().currentUserLanguage()),
                stackTrace);
    }

    private RestfulApiResponse handlerBizExceptionWithArguments(BizExceptionWithArguments bizEx) {
        String stackTrace = _debug ? getStackTrace(bizEx) : "";
        return RestfulApiResponse.failure(
                bizEx.getErrorCode().getCode(),
                bizEx.getErrorCode().getInternationalMessage(BizDataInterface.getBean().currentUserLanguage(), bizEx.getArgs().toArray()),
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
}
