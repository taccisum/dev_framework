package com.cloudlinkscm.loms.framework.webapp.response.processer.adapter;

import com.cloudlinkscm.loms.framework.webapp.exception.RepeatAdapterException;

import java.util.HashMap;
import java.util.Map;

/**
 * 响应适配器工厂，根据类别返回不同的{@link ResponseAdapter}实例
 *
 * @author : tac
 * @date : 2017/5/17
 */
public class ResponseAdapterFactory {
    private static Map<Integer, ResponseAdapter> adapters = new HashMap<>();
    static {
        adapters.put(0, new Response4ClientAdapter());
    }

    /**
     * 在实际应用中可通过此方法扩展自定义的adapter
     *
     * @param type 适配器类型，重复定义会抛出异常
     * @param adapter 自定义的适配器
     */
    public static void put(Integer type, ResponseAdapter adapter) throws RepeatAdapterException {
        if (adapters.get(type) != null) {
            throw new RepeatAdapterException("exist type: " + type);
        }

        adapters.put(type, adapter);
    }

    public static ResponseAdapter create(Integer type){
        ResponseAdapter adapter = adapters.get(type);
        if(adapter == null){
            return adapters.get(0);
        }else{
            return adapter;
        }
    }
}
