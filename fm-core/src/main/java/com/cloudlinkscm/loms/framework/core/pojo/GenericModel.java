package com.cloudlinkscm.loms.framework.core.pojo;

import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

/**
 * 通用实体模型基类
 *
 * 如果是与业务相关的实体模型请使用{@link GenericBizModel}
 *
 * @author : tac
 * @date : 2017/5/13
 */

public abstract class GenericModel implements Serializable {
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

    /**
     * 用于初始化一个新的实体某些字段的默认值
     * @see #generateId()
     * @see #setDefault()
     */
    public final void init(){
        Date currentDate = new Date();

        setId(generateId());
        setInsertTime(currentDate);
//        setInsertUser("");
        setUpdateUser(null);
        setUpdateTime(null);

        setDefault();
    }

    /**
     * 在派生类中可以通过改写该方法来为某些字段赋于默认值
     */
    protected void setDefault(){
    }

    protected String generateId() {
        return UUID.randomUUID().toString().replace("-","");
    }
}
