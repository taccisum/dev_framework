package com.cloudlinkscm.loms.framework.core.pojo;

import io.swagger.annotations.ApiModelProperty;

/**
 * restful api统一响应model
 * @author : tac
 * @date : 2017/5/13
 */
public class RestfulApiResponse<T> {
    public static final String SUCCESS_CODE = "0";
    public static final int SUCCESS_STATE = 0;      //成功
    public static final int FAILURE_STATE = 1;      //失败（业务异常）
    public static final int ERROR_STATE = -1;       //错误（系统异常）

    @ApiModelProperty(value = "请求执行状态", required = true, example = "0", allowableValues = "-1, 0, 1")
    private Integer returnCode;
    @ApiModelProperty(value = "具体的错误码", example = "0", allowableValues = "range[0, 50000]")
    private String errorCode;
    @ApiModelProperty(value = "错误提示信息", example = "操作成功")
    private String returnMsg;
    @ApiModelProperty(value = "返回数据", required = true, example = "{}")
    private T result;
    /**
     * 堆栈追踪信息（请仅在debug模式下返回此值，否则应为空字符串）
     */
    @ApiModelProperty(value = "堆栈追踪信息", example = "")
    private String stackTrace;

    public RestfulApiResponse(){
    }

    public RestfulApiResponse(Integer returnCode, String errorCode, String returnMsg) {
        this.returnCode = returnCode;
        this.errorCode = errorCode;
        this.returnMsg = returnMsg;
    }

    public RestfulApiResponse(Integer returnCode, String errorCode, String returnMsg, T result) {
        this.returnCode = returnCode;
        this.errorCode = errorCode;
        this.returnMsg = returnMsg;
        this.result = result;
    }

    public RestfulApiResponse(Integer returnCode, String errorCode, String returnMsg, String stackTrace) {
        this.returnCode = returnCode;
        this.errorCode = errorCode;
        this.returnMsg = returnMsg;
        this.stackTrace = stackTrace;
    }

    public Integer getReturnCode() {
        return returnCode;
    }

    public void setReturnCode(Integer returnCode) {
        this.returnCode = returnCode;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getReturnMsg() {
        return returnMsg;
    }

    public void setReturnMsg(String returnMsg) {
        this.returnMsg = returnMsg;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    public String getStackTrace() {
        return stackTrace;
    }

    public void setStackTrace(String stackTrace) {
        this.stackTrace = stackTrace;
    }

    public static <T> RestfulApiResponse<T> success(String message, T data){
        return new RestfulApiResponse<>(SUCCESS_STATE, SUCCESS_CODE, message, data);
    }

    public static <T> RestfulApiResponse<T> success(T data){
        return new RestfulApiResponse<>(SUCCESS_STATE, SUCCESS_CODE, "操作成功", data);
    }

    public static <T> RestfulApiResponse<T> success(String message){
        return new RestfulApiResponse<>(SUCCESS_STATE, SUCCESS_CODE, message);
    }

    public static <T> RestfulApiResponse<T> success(){
        return new RestfulApiResponse<>(SUCCESS_STATE, SUCCESS_CODE, "操作成功");
    }

    public static <T> RestfulApiResponse<T> failure(String code, String message){
        return new RestfulApiResponse<>(FAILURE_STATE, code, message, "");
    }

    public static <T> RestfulApiResponse<T> failure(String code, String message, String stackTrace){
        return new RestfulApiResponse<>(FAILURE_STATE, code, message, stackTrace);
    }

    public static <T> RestfulApiResponse<T> error(String message, String stackTrace){
        return new RestfulApiResponse<>(ERROR_STATE, "-1", message, stackTrace);
    }
}
