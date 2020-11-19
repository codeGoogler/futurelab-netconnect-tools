package com.net.tools.futurelabnetconnecttools.common.req;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author 衡钊清
 * @Classname AbstractResponse
 * @Description 请求响应
 * @Date 2020/2/12 10:54
 */
@Data
@ToString
public abstract class AbstractResponse implements Serializable {

    private static final long serialVersionUID = -2614840790448269529L;

    /**
     * 响应编码
     */
    private String code;

    /**
     * 响应信息
     */
    private String msg;

    public AbstractResponse() {
    }

    public AbstractResponse(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
