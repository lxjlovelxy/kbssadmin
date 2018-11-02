package com.kbss.admin.cms.entity;

import java.util.Date;
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
 * 学校用户表
 * </p>
 *
 * @author qrf
 * @since 2018-10-27
 */
@Data
@Accessors(chain = true)
public class UsrProject extends Model<UsrProject> {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @TableId
    private Long id;
    /**
     * 父ID
     */
    private Long parentId;
    /**
     * 项目ID
     */
    private Long projectId;
    /**
     * 用户ID
     */
    private Long userId;
    /**
     * 角色ID
     */
    private Long roleId;
    /**
     * 帐号
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

    public static final String PARENT_ID = "parent_id";

    public static final String PROJECT_ID = "project_id";

    public static final String USER_ID = "user_id";

    public static final String ROLE_ID = "role_id";

    public static final String ACC = "acc";

    public static final String PHONE = "phone";

    public static final String EMAIL = "email";

    public static final String NICK_NAME = "nick_name";

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
