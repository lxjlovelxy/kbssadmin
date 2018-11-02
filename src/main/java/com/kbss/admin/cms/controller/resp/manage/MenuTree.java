package com.kbss.admin.cms.controller.resp.manage;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * <p></p>
 * <p>Created by qrf on 2018/10/24.</p>
 *
 * @author qrf
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MenuTree {

    private Long id;

    private Long parentId;

    private Integer grade;

    private Integer pos;

    private String code;

    private String name;

    private String ico;

    private String url;

    private List<MenuTree> children;
}
