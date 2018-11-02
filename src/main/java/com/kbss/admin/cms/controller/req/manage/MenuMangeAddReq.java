package com.kbss.admin.cms.controller.req.manage;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotBlank;

/**
 * <p></p>
 * <p>Created by qrf on 2018/10/27.</p>
 *
 * @author qrf
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MenuMangeAddReq {

    private Long id;

    private Long parentId;

    private String code;

    private String name;

    private String ico;

    private String url;

    private Long menuTypeId;
}
