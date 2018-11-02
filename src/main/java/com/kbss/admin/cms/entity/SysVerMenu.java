package com.kbss.admin.cms.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.Version;

import com.kbss.admin.cms.enums.RoleType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 系统版本菜单表
 * </p>
 *
 * @author qrf
 * @since 2018-10-27
 */
@Data
@Accessors(chain = true)
public class SysVerMenu extends Model<SysVerMenu> {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @TableId
    private Long id;

    private Long menuId;
    /**
     * 角色类型  1:系统 2:学校 3:站点 4:学员  5:教师
     */
    private RoleType roleType;


    public static final String ID = "id";

    public static final String MENU_ID = "menu_id";

    public static final String ROLE_TYPE = "role_type";

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
