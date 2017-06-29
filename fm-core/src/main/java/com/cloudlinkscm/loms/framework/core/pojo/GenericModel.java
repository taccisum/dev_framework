package com.cloudlinkscm.loms.framework.core.pojo;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OrderBy;
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
 * @see IGenericModel
 * @author : tac
 * @date : 2017/5/13
 */

@Entity
public abstract class GenericModel<PK> implements IGenericModel<PK>, Serializable {
    @Id
    private PK id;
    private String insertUser;
    @OrderBy("desc")
    private Date insertTime;
    private String updateUser;
    private Date updateTime;

    @Override
    public PK getId() {
        return id;
    }

    @Override
    public void setId(PK id) {
        this.id = id;
    }

    @Override
    public String getInsertUser() {
        return insertUser;
    }

    @Override
    public void setInsertUser(String insertUser) {
        this.insertUser = insertUser;
    }

    @Override
    public Date getInsertTime() {
        return insertTime;
    }

    @Override
    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }

    @Override
    public String getUpdateUser() {
        return updateUser;
    }

    @Override
    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    @Override
    public Date getUpdateTime() {
        return updateTime;
    }

    @Override
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * {@inheritDoc}
     *
     * @see #generateId()
     * @see #setDefault()
     */
    @Override
    public final void init(){
        Date currentDate = new Date();

        setId(generateId());
        setInsertTime(currentDate);
        //todo:: 后续若把BizDataInterface迁移到fm-core模块中，则可以在此处直接给insertUser赋值
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
