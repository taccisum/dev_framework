package com.cloudlinkscm.loms.framework.core.exception;

import com.cloudlinkscm.loms.framework.core.pojo.ErrorCode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 带参数的业务异常基类
 *
 * @author : tac
 * @date : 2017/5/12
 */
public class BizExceptionWithArguments extends BizException {
    private List<String> args = new ArrayList<>();

    public List<String> getArgs() {
        return args;
    }

    public BizExceptionWithArguments(ErrorCode errorCode, String... args) {
        super(errorCode);
        this.args = Arrays.asList(args);
    }
}
