package com.cloudlinkscm.loms.framework.webapp.response.processer.adapter;


import com.cloudlinkscm.loms.framework.core.pojo.RestfulApiResponse;

/**
 * 默认的客户端请求响应适配器（不做任何适配直接返回）
 *
 * @author : tac
 * @date : 2017/5/17
 */
public class Response4ClientAdapter implements ResponseAdapter<RestfulApiResponse> {
    @Override
    public RestfulApiResponse doAdapt(RestfulApiResponse response) {
        return response;
    }
}
