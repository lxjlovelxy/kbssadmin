package com.kbss.admin.cms.entity;

import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.Version;

import com.kbss.admin.cms.enums.YesOrNo;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 学生用户表
 * </p>
 *
 * @author qrf
 * @since 2018-10-27
 */
@Data
@Accessors(chain = true)
public class UsrStudent extends Model<UsrStudent> {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @TableId
    private Long id;
    /**
     * 帐号 字母与数据组成,且以字母开头,且不区分大小写
     */
    private String acc;
    /**
     * 手机号
     */
    private String phone;
    /**
     * Email
     */
    private String email;
    /**
     * 昵称
     */
    private String nickName;
    /**
     * 激活状态 0:未激活 1:已激活
     */
    private YesOrNo activeState;
    /**
     * 激活时间
     */
    @TableField("Active_Time")
    private String activeTime;
    /**
     * 激活码
     */
    @TableField("Active_Code")
    private String activeCode;
    /**
     * 激活码有效期
     */
    @TableField("Active_Code_Time")
    private String activeCodeTime;
    /**
     * 激活次数
     */
    @TableField("Active_Cnt")
    private Integer activeCnt;
    /**
     * 身份证明类型
     */
    @TableField("ID_Style")
    private String idStyle;
    /**
     * 身份证明号码
     */
    @TableField("ID_Num")
    private String idNum;
    /**
     * 姓名
     */
    @TableField("real_Name")
    private String realName;
    /**
     * 实名状态 0:未实名 1:已实名
     */
    @TableField("real_State")
    private Integer realState;
    /**
     * 备注
     */
    private String memo;
    /**
     * 状态 0：停用 1：启用
     */
    private YesOrNo state;
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

    public static final String ACC = "acc";

    public static final String PHONE = "phone";

    public static final String EMAIL = "email";

    public static final String NICK_NAME = "nick_name";

    public static final String ACTIVE_STATE = "active_state";

    public static final String ACTIVE_TIME = "Active_Time";

    public static final String ACTIVE_CODE = "Active_Code";

    public static final String ACTIVE_CODE_TIME = "Active_Code_Time";

    public static final String ACTIVE_CNT = "Active_Cnt";

    public static final String ID_STYLE = "ID_Style";

    public static final String ID_NUM = "ID_Num";

    public static final String REAL_NAME = "real_Name";

    public static final String REAL_STATE = "real_State";

    public static final String MEMO = "memo";

    public static final String STATE = "state";

    public static final String GMT_CREATER = "gmt_creater";

    public static final String GMT_MODIFIER = "gmt_modifier";

    public static final String GMT_CREATE = "gmt_create";

    public static final String GMT_MODIFIED = "gmt_modified";

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
