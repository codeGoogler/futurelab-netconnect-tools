package com.net.tools.futurelabnetconnecttools.common.req;

import lombok.Data;

/**
 * @author 衡钊清
 * @Classname BaseResponse
 * @Description 基础返回数据封装
 * @Date 2020/2/12 17:47
 */
@Data
public class CommonResponse<T> extends AbstractResponse {

    /**
     * 响应的数据
     */
    private T data;

    public CommonResponse() {
    }

    public CommonResponse(T data) {
        this.data = data;
    }

    public CommonResponse(String code, String msg, T data) {
        super(code, msg);
        this.data = data;
    }
}
