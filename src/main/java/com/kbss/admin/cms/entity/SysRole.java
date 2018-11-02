package com.kbss.admin.cms.entity;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotations.Version;

import com.kbss.admin.cms.enums.RoleType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 系统角色表
 * </p>
 *
 * @author qrf
 * @since 2018-10-27
 */
@Data
@Accessors(chain = true)
public class SysRole extends Model<SysRole> {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @TableId
    private Long id;
    /**
     * 角色类型  1:系统 2:学校 3:站点 4:学员  5:教师
     */
    private RoleType roleType;
    /**
     * 机构ID(项目ID、站点ID等)
     */
    private Long orgId;
    /**
     * 编码
     */
    @TableField("Code")
    private String Code;
    /**
     * 名称
     */
    @TableField("Name")
    private String Name;
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

    public static final String ROLE_TYPE = "role_type";

    public static final String ORG_ID = "org_id";

    public static final String CODE = "Code";

    public static final String NAME = "Name";

    public static final String GMT_CREATER = "gmt_creater";

    public static final String GMT_MODIFIER = "gmt_modifier";

    public static final String GMT_CREATE = "gmt_create";

    public static final String GMT_MODIFIED = "gmt_modified";

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
