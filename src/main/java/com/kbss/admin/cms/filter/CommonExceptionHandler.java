package com.kbss.admin.cms.filter;



import com.kbss.admin.cms.controller.resp.CommonResp;
import com.kbss.admin.cms.filter.entity.CommonError;
import com.kbss.admin.cms.filter.entity.CommonException;
import com.kbss.admin.cms.filter.entity.ErrorEnums;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * <p>全局异常拦截</p>
 * <p>Created by qrf on 2017/8/16.</p>
 */
@ControllerAdvice
@Slf4j
public class CommonExceptionHandler {

    /**
     * 自定义异常统一处理
     * @param commonException
     * @return
     */
    @ExceptionHandler(CommonException.class)
    @ResponseBody
    public CommonResp handleCommonException(CommonException commonException){
        CommonError commonError = new CommonError(commonException);
        log.error("统一异常:{}", commonException);
        return new CommonResp(commonError);
    }

    /**
     * 字段校验异常统一处理
     * @param ex
     * @return
     */
    @ExceptionHandler(BindException.class)
    @ResponseBody
    public CommonResp handleBindException(BindException ex){
        FieldError fieldError = ex.getBindingResult().getFieldError();
        CommonError commonError = new CommonError(ErrorEnums.CHECK_FAIL, fieldError.getField(),fieldError.getDefaultMessage());
        log.error("参数校验异常:{}",ex);
        return new CommonResp(commonError);
    }

    /**
     * 字段校验异常统一处理
     * @param ex
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public CommonResp handleBindException(MethodArgumentNotValidException ex){
        FieldError fieldError = ex.getBindingResult().getFieldError();
        CommonError commonError = new CommonError(ErrorEnums.CHECK_FAIL, fieldError.getField(),fieldError.getDefaultMessage());
        log.error("参数校验异常:{}",ex);
        return new CommonResp(commonError);
    }

    /**
     * 字段校验异常统一处理
     * @param ex
     * @return
     */
    @ExceptionHandler(MissingServletRequestParameterException.class)
    @ResponseBody
    public CommonResp handleBindException(MissingServletRequestParameterException ex){
        CommonError commonError = new CommonError(ErrorEnums.CHECK_FAIL, ex.getParameterName(),ex.getMessage());
        log.error("参数校验异常:{}",ex);
        return new CommonResp(commonError);
    }

    /**
     * 异常处理
     * @param ex
     * @return
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public CommonResp handleException(Exception ex){
        CommonError commonError = new CommonError(ErrorEnums.SYS_ERROR,ex.getMessage());
        log.error("系统异常:{}",ex);
        return new CommonResp(commonError);
    }
}
