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
 * 数据字典表
 * </p>
 *
 * @author qrf
 * @since 2018-10-27
 */
@Data
@Accessors(chain = true)
public class SysDataDic extends Model<SysDataDic> {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @TableId
    private Long id;
    /**
     * 机构ID(项目ID、站点ID等)
     */
    private Long orgId;
    /**
     * 分类
     */
    private String style;
    /**
     * 编码
     */
    private String code;
    /**
     * 描述
     */
    private String name;
    /**
     * 值
     */
    private String value;
    /**
     * 值2
     */
    private Integer value2;
    /**
     * 值3
     */
    private Integer value3;
    /**
     * 备注
     */
    private String memo;
    /**
     * 备注
     */
    private String memo2;
    /**
     * 备注
     */
    private String memo3;
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

    public static final String STYLE = "style";

    public static final String CODE = "code";

    public static final String NAME = "name";

    public static final String VALUE = "value";

    public static final String VALUE2 = "value2";

    public static final String VALUE3 = "value3";

    public static final String MEMO = "memo";

    public static final String MEMO2 = "memo2";

    public static final String MEMO3 = "memo3";

    public static final String ORG_ID = "org_id";

    public static final String GMT_CREATER = "gmt_creater";

    public static final String GMT_MODIFIER = "gmt_modifier";

    public static final String GMT_CREATE = "gmt_create";

    public static final String GMT_MODIFIED = "gmt_modified";

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
