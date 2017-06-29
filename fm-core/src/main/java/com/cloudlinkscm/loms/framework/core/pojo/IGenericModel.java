package com.cloudlinkscm.loms.framework.core.pojo;

import java.util.Date;
import javax.persistence.Id;
import javax.persistence.Column;
/**
 * 定义通用的model应具有的getter和setter及其它通用方法
 *
 * <br/>
 * 继承此接口后的类应该：
 * <ul>
 *     <li>定义getter/setter相对应的字段，且字段名称必须与getter/setter一致</li>
 *     <li>使用{@link Id}指定一个主键</li>
 *     <li>如有必要，可自行实现更多操作（例如使用{@link Column}手动指定映射到model字段名的表名）</li>
 * </ul>
 *
 * @author : tac
 * @date : 2017/6/29
 */
public interface IGenericModel<PK> {
    //getter or setter
    PK getId();
    void setId(PK id);

    String getInsertUser();
    void setInsertUser(String insertUser);

    Date getInsertTime();
    void setInsertTime(Date insertTime);

    String getUpdateUser();
    void setUpdateUser(String updateUser);

    Date getUpdateTime();
    void setUpdateTime(Date updateTime);

    //其它通用方法

    /**
     * 用于初始化一个新的实体某些字段的默认值
     */
    void init();
}
