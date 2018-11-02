package com.kbss.admin.cms.filter.entity;

/**
 * <p></p>
 * <p>Created by qrf on 2018/10/25.</p>
 *
 * @author qrf
 */
public enum ErrorEnums {

    LOGIN_FAIL("100000", "用户名或密码错误"),
    NO_USER("100001", "登录用户不存在"),
    USER_DISABLE("100002", "登录账户已停用，请联系管理员"),

    DB_UPDATE_FAIL("200000", "数据库更新失败"),
    DB_DEL_FAIL("200001", "数据库删除失败"),
    DB_ADD_FAILL("200002", "数据库新增失败"),
    DB_ADD_EXIST("200003", "数据库新增失败,[%s]数据已存在"),
    DB_UPDATE_EXIST("200004", "数据库更新失败,[%s]数据已存在"),

    PID_NO_EXIST("300000", "父菜单不存在"),
    MENU_NO_EXIST("300001", "菜单不存在"),
    MENU_NO_MOVE("300002", "菜单无法移动"),

    SYS_ERROR("000000","系统异常:%s"),
    CHECK_FAIL("000001","参数校验失败[%s]%s"),
    NO_LOGIN("000002","未登录，请重新登录！"),
    SESSION_TIMEOUT("000003","登录超时，请重新登录！"),
    NO_PERMISSON("000004","无此权限");

    private String code;

    private String msg;

    ErrorEnums(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
