package com.cloudlinkscm.loms.framework.core.pojo;

/**
 * restful api统一响应model
 * @author : tac
 * @date : 2017/5/13
 */
public class RestfulApiResponse<T> {
    private static final String SUCCESS_CODE = "1";
    private static final int SUCCESS_STATE = 1;
    private static final int FAILURE_STATE = 0;
    private static final int ERROR_STATE = -1;
    /**
     * 执行状态
     * 1：成功
     * 0：失败（业务异常）
     * -1：错误（系统异常）
     */
    private Integer returnCode;
    /**
     * 错误码
     */
    private String errorCode;
    /**
     * 提示信息
     */
    private String returnMsg;
    /**
     * 返回数据
     */
    private T result;
    /**
     * 堆栈追踪信息（请仅在debug模式下返回此值，否则应为空字符串）
     */
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
