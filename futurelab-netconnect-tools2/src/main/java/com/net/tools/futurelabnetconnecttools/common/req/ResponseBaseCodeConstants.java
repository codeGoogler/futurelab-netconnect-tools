package com.net.tools.futurelabnetconnecttools.common.req;

/**
 * @author 衡钊清
 * @Classname ResponseBaseCodeConstants
 * @Description TODO
 * @Date 2020/2/12 15:54
 */
public enum ResponseBaseCodeConstants {

    SUCCESS("SUCCESS", "成功！"),

    SERVICE_ERROR("SERVICE_ERROR", "服务繁忙！"),

    INVALID_PARAMETER("INVALID_PARAMETER", "请求参数缺失！");

    /**
     * 响应吗
     */
    private String code;

    /**
     * 提示信息
     */
    private String msg;

    ResponseBaseCodeConstants(String code, String msg) {
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
