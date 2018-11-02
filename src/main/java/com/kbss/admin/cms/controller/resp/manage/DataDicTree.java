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
public class DataDicTree {
    private String style;

    private List<DataDicTree> children;
}
