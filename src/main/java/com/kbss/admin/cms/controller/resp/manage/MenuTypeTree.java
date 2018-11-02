package com.kbss.admin.cms.controller.resp.manage;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MenuTypeTree {
    private Long id;

    private String name;

    private String code;

    private List<MenuTypeTree> children;
}
