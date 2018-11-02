package com.kbss.admin.cms.controller.resp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * <p></p>
 * <p>Created by qrf on 2018/10/26.</p>
 *
 * @author qrf
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PageResp<T> {

    private Integer index;

    private Integer size;

    private Integer total;

    private List<T> rows;
}
