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
 * 层次表
 * </p>
 *
 * @author qrf
 * @since 2018-11-02
 */
@Data
@Accessors(chain = true)
public class EduBscLevel extends Model<EduBscLevel> {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 项目ID
     */
    private Long projectId;
    /**
     * 编码
     */
    private String code;
    /**
     * 名称
     */
    private String name;
    /**
     * 拼音简写
     */
    private String shortName;
    /**
     * 备注
     */
    private String memo;
    /**
     * 状态 0：停用 1：启用
     */
    private Integer state;
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

    public static final String PROJECT_ID = "project_id";

    public static final String CODE = "code";

    public static final String NAME = "name";

    public static final String SHORT_NAME = "short_name";

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
