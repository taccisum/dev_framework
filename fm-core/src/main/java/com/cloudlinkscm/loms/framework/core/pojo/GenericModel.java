package com.cloudlinkscm.loms.framework.core.pojo;

import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;
import com.cloudlinkscm.loms.framework.util.BeanUtils;

/**
 * 通用实体模型基类
 *
 * <p>如果是与业务相关的实体模型请使用{@link GenericBizModel}</p>
 *
 * <p>
 *     由于使用了泛型主键，在运行时id的类型将被擦除为{@link Object}，因此使用{@link BeanUtils}
 *     将实体类的值复制到dto对象时将无法正常复制id，解决方案有二：
 *     <ul>
 *         <li>在实际代码中手动赋值：{@code dto.setId(entity.getId())}</li>
 *         <li>将dto中的id类型更改为{@link Object}</li>
 *     </ul>
 * </p>
 *
 * @author : tac
 * @date : 2017/5/13
 */

public abstract class GenericModel<PK> implements Serializable {
    @Id
    private PK id;
    private String insertUser;
    private Date insertTime;
    private String updateUser;
    private Date updateTime;

    public PK getId() {
        return id;
    }

    public void setId(PK id) {
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

    protected abstract PK generateId();
}
