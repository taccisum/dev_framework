package com.cloudlinkscm.loms.framework.webapp.response.processer.adapter;


import com.cloudlinkscm.loms.framework.core.pojo.RestfulApiResponse;

/**
 * @author : tac
 * @date : 2017/5/17
 */
public interface ResponseAdapter<T> {
    T doAdapt(RestfulApiResponse response);
}
