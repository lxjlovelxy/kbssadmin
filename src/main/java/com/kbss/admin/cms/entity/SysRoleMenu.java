package com.kbss.admin.cms.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.Version;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 系统角色菜单表
 * </p>
 *
 * @author qrf
 * @since 2018-10-27
 */
@Data
@Accessors(chain = true)
public class SysRoleMenu extends Model<SysRoleMenu> {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @TableId
    private Long id;

    private Long menuId;
    /**
     * 角色ID
     */
    private Long roleId;
    /**
     * 菜单名称
     */
    private String menuName;


    public static final String ID = "id";

    public static final String MENU_ID = "menu_id";

    public static final String ROLE_ID = "role_id";

    public static final String MENU_NAME = "menu_name";

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
