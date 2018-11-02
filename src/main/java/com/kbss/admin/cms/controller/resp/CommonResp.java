package com.kbss.admin.cms.controller.resp;

import com.kbss.admin.cms.filter.entity.CommonError;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <p></p>
 * <p>Created by qrf on 2017/9/23.</p>
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommonResp<T> {

    private Boolean state;

    private T data;

    private CommonError error;

    public CommonResp(T data) {
        this.state = true;
        this.data = data;
    }

    public CommonResp(CommonError error){
        this.state = false;
        this.error = error;
    }
}
