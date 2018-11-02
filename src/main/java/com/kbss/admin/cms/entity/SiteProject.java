package com.kbss.admin.cms.entity;

import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.activerecord.Model;
import java.io.Serializable;


import com.kbss.admin.cms.enums.YesOrNo;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * <p>
 * 站点项目表
 * </p>
 *
 * @author qrf
 * @since 2018-10-27
 */
@Data
@Accessors(chain = true)
public class SiteProject extends Model<SiteProject> {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @TableId
    private Long id;
    /**
     * 站点ID
     */
    private Long siteId;
    /**
     * 项目ID
     */
    private Long projectId;
    /**
     * 机构ID
     */
    private Long orgId;
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

    public static final String SITE_ID = "site_id";

    public static final String PROJECT_ID = "project_id";

    public static final String ORG_ID = "org_id";

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
