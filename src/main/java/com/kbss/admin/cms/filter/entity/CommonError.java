package com.kbss.admin.cms.filter.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <p></p>
 * <p>Created by qrf on 2018/10/25.</p>
 *
 * @author qrf
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommonError {

    private String code;

    private String msg;

    public CommonError(CommonException ex){
        this.code = ex.getCode();
        this.msg = ex.getMsg();
    }

    public CommonError(ErrorEnums errorEnums) {
        this(errorEnums.getCode(),errorEnums.getMsg());
    }

    public CommonError(ErrorEnums errorEnums, Object... args){
        this(errorEnums.getCode(),String.format(errorEnums.getMsg(),args));
    }
}
