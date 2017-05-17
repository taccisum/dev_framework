package com.cloudlinkscm.loms.framework.webapp.response.processer.adapter;


import com.cloudlinkscm.loms.framework.core.pojo.RestfulApiResponse;

/**
 * @author : tac
 * @date : 2017/5/17
 */
public class Response4ClientAdapter implements ResponseAdapter<RestfulApiResponse> {
    @Override
    public RestfulApiResponse doAdapt(RestfulApiResponse response) {
        return response;
    }
}
