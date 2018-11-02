package com.kbss.admin.cms.filter.entity;

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
public class CommonException extends Exception{

    private String code;

    private String msg;

    public CommonException(String code,String msg){
        super(code+msg);
        this.code = code;
        this.msg = msg;
    }

    public CommonException(ErrorEnums errorEnums) {
        this(errorEnums.getCode(),errorEnums.getMsg());
    }

    public CommonException(ErrorEnums errorEnums, Object... args){
        this(errorEnums.getCode(),String.format(errorEnums.getMsg(),args));
    }
}
