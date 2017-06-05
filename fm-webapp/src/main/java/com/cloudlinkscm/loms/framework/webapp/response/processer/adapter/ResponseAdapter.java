package com.cloudlinkscm.loms.framework.webapp.response.processer.adapter;


import com.cloudlinkscm.loms.framework.core.pojo.RestfulApiResponse;

/**
 * 定义响应适配器接口
 *
 * @author : tac
 * @date : 2017/5/17
 */
public interface ResponseAdapter<T> {
    T doAdapt(RestfulApiResponse response);
}
