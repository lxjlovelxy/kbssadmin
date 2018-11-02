package com.kbss.admin.cms.entity;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.activerecord.Model;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotations.Version;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 系统菜单表
 * </p>
 *
 * @author qrf
 * @since 2018-10-27
 */
@Data
@Accessors(chain = true)
public class SysMenu extends Model<SysMenu> {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @TableId
    private Long id;
    /**
     * 类型 0:分类 1:功能模块
     */
    private Long parentId;
    /**
     * 名称
     */
    private String name;
    /**
     * URL
     */
    private String url;
    /**
     * 图标
     */
    private String ico;
    /**
     * 菜单code
     */
    private String code;
    /**
     * 等级
     */
    private Integer grade;
    /**
     * 位置
     */
    private Integer pos;
    /**
     * 分类ID
     */
    private Long styleId;
    /**
     * 备注
     */
    private String memo;
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

    public static final String NAME = "name";

    public static final String URL = "url";

    public static final String ICO = "ico";

    public static final String CODE = "code";

    public static final String GRADE = "grade";

    public static final String POS = "pos";

    public static final String STYLE_ID = "style_id";

    public static final String MEMO = "memo";

    public static final String GMT_CREATER = "gmt_creater";

    public static final String GMT_MODIFIER = "gmt_modifier";

    public static final String GMT_CREATE = "gmt_create";

    public static final String GMT_MODIFIED = "gmt_modified";

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
