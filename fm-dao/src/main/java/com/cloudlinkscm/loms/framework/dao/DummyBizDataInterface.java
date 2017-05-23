package com.cloudlinkscm.loms.framework.dao;

import com.cloudlinkscm.loms.framework.core.pojo.Language;

/**
 * @author : tac
 * @date : 2017/5/23
 */
public class DummyBizDataInterface implements BizDataInterface {
    private static DummyBizDataInterface instance;

    private DummyBizDataInterface(){
    }

    public static BizDataInterface instance(){
        if(instance == null){
            instance = new DummyBizDataInterface();
        }
        return instance;
    }

    @Override
    public String currentUserId() {
        return "";
    }

    @Override
    public String currentUserTenantId() {
        return "";
    }

    @Override
    public Language currentUserLanguage() {
        return Language.ZH;
    }
}
