package com.cloudlinkscm.loms.framework.core.pojo;

/**
 * restful api统一响应model
 * @author : tac
 * @date : 2017/5/13
 */
public class RestfulApiResponse<T> {
    private static final int SUCCESS_STATE = 1;
    private static final int FAILURE_STATE = 0;
    private static final int ERROR_STATE = -1;
    /**
     * 执行状态
     * 1：成功
     * 0：失败（业务异常）
     * -1：错误（系统异常）
     */
    private Integer state;
    /**
     * 错误码
     */
    private String code;
    /**
     * 提示信息
     */
    private String message;
    /**
     * 返回数据
     */
    private T data;
    /**
     * 堆栈追踪信息（请仅在debug模式下返回此值，否则应为空字符串）
     */
    private String stackTrace;

    public RestfulApiResponse(Integer state, String code, String message) {
        this.state = state;
        this.code = code;
        this.message = message;
    }

    public RestfulApiResponse(Integer state, String code, String message, T data) {
        this.state = state;
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public RestfulApiResponse(Integer state, String code, String message, String stackTrace) {
        this.state = state;
        this.code = code;
        this.message = message;
        this.stackTrace = stackTrace;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getStackTrace() {
        return stackTrace;
    }

    public void setStackTrace(String stackTrace) {
        this.stackTrace = stackTrace;
    }

    public static <T> RestfulApiResponse<T> success(String code, String message, T data){
        return new RestfulApiResponse<>(SUCCESS_STATE, code, message, data);
    }

    public static <T> RestfulApiResponse<T> success(String code, T data){
        return new RestfulApiResponse<>(SUCCESS_STATE, code, "操作成功", data);
    }

    public static <T> RestfulApiResponse<T> success(String code, String message){
        return new RestfulApiResponse<>(SUCCESS_STATE, code, message);
    }

    public static <T> RestfulApiResponse<T> success(String code){
        return new RestfulApiResponse<>(SUCCESS_STATE, code, "操作成功");
    }

    public static <T> RestfulApiResponse<T> failure(String code, String message, String stackTrace){
        return new RestfulApiResponse<>(FAILURE_STATE, code, message, stackTrace);
    }

    public static <T> RestfulApiResponse<T> error(String code, String message, String stackTrace){
        return new RestfulApiResponse<>(ERROR_STATE, code, message, stackTrace);
    }
}
