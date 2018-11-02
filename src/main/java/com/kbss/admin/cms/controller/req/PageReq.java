package com.kbss.admin.cms.controller.req;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
public class PageReq<T> {

    private Integer index;

    private Integer size;

    private T query;
}
