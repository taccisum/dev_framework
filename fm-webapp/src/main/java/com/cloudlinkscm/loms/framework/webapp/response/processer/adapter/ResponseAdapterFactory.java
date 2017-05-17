package com.cloudlinkscm.loms.framework.webapp.response.processer.adapter;

import java.util.HashMap;
import java.util.Map;

/**
 * @author : tac
 * @date : 2017/5/17
 */
public class ResponseAdapterFactory {
    private static Map<Integer, ResponseAdapter> adapters = new HashMap<>();
    static {
        adapters.put(0, new Response4ClientAdapter());
    }

    /**
     * 在实际应用中可通过此方法扩展需要的adapter
     * @param type
     * @param adapter
     */
    public static void put(Integer type, ResponseAdapter adapter) throws Exception {
        if (adapters.get(type) != null) {
            throw new Exception("type: " + type + "is existed in collection");
        }

        adapters.put(type, adapter);
    }

    public static ResponseAdapter create(Integer type){
        ResponseAdapter adapter = adapters.get(type);
        if(adapter == null){
            return new Response4ClientAdapter();
        }else{
            return adapter;
        }
    }
}
