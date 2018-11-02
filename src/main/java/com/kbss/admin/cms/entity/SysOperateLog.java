package com.kbss.admin.cms.entity;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotations.Version;

import com.kbss.admin.cms.enums.YesOrNo;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 操作日志表
 * </p>
 *
 * @author qrf
 * @since 2018-10-27
 */
@Data
@Accessors(chain = true)
public class SysOperateLog extends Model<SysOperateLog> {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @TableId
    private Long id;
    /**
     * 操作日期 分区条件
     */
    @TableField("oprDate")
    private String oprDate;
    /**
     * 提交IP
     */
    @TableField("IP")
    private String ip;
    /**
     * 动作
     */
    @TableField("Operate")
    private String Operate;
    /**
     * 动作说明
     */
    @TableField("OperateInf")
    private String OperateInf;
    /**
     * 动作参数
     */
    @TableField("OperateParams")
    private String OperateParams;
    /**
     * 状态 0：停用 1：启用
     */
    private YesOrNo state;
    /**
     * 用户账号
     */
    @TableField("UserCode")
    private Long UserCode;
    /**
     * 用户名称
     */
    @TableField("UserName")
    private String UserName;
    /**
     * 创建人
     */
    private Long gmtCreater;
    /**
     * 更新人
     */
    private Long gmtModifier;
    /**
     * 创建时间
     */
    private Date gmtCreate;
    /**
     * 更新时间
     */
    private Date gmtModified;


    public static final String ID = "id";

    public static final String OPRDATE = "oprDate";

    public static final String IP = "IP";

    public static final String OPERATE = "Operate";

    public static final String OPERATEINF = "OperateInf";

    public static final String OPERATEPARAMS = "OperateParams";

    public static final String STATE = "state";

    public static final String USERCODE = "UserCode";

    public static final String USERNAME = "UserName";

    public static final String GMT_CREATER = "gmt_creater";

    public static final String GMT_MODIFIER = "gmt_modifier";

    public static final String GMT_CREATE = "gmt_create";

    public static final String GMT_MODIFIED = "gmt_modified";

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
