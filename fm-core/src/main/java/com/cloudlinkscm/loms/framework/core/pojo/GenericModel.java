package com.cloudlinkscm.loms.framework.core.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Id;
import java.util.Date;
import java.util.UUID;

/**
 * 实体模型基类
 * @author : tac
 * @date : 2017/5/13
 */

public abstract class GenericModel {
    @Id
    private String id;
    private String insertUser;
    private Date insertTime;
    private String updateUser;
    private Date updateTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getInsertUser() {
        return insertUser;
    }

    public void setInsertUser(String insertUser) {
        this.insertUser = insertUser;
    }

    public Date getInsertTime() {
        return insertTime;
    }

    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public final void init(){
        Date currentDate = new Date();

        setId(generateId());
        setInsertTime(currentDate);
//        setInsertUser("");
        setUpdateUser(null);
        setUpdateTime(null);

        setDefault();
    }

    protected void setDefault(){
    }

    protected String generateId() {
        return UUID.randomUUID().toString().replace("-","");
    }
}
