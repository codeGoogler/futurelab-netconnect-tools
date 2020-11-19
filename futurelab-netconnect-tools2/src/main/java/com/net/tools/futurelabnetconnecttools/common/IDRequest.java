package com.net.tools.futurelabnetconnecttools.common;


import com.net.tools.futurelabnetconnecttools.common.exception.support.ParamsValidateException;
import com.net.tools.futurelabnetconnecttools.common.req.ResponseBaseCodeConstants;
import lombok.Data;
import lombok.ToString;

/**
 * @author 衡钊清
 * @Classname IDRequest
 * @Description id参数请求
 * @Date 2020/2/12 10:48
 */
@Data
@ToString(callSuper = true)
public class IDRequest extends  AbstractRequest {

    /**
     * 主键
     */
    private Integer id;

    public IDRequest() {
    }

    public IDRequest(Integer id) {
        this.id = id;
    }

    @Override
    public void checkParams() {
        if (id == null){
            throw new ParamsValidateException(
                    ResponseBaseCodeConstants.INVALID_PARAMETER.getCode(),
                    ResponseBaseCodeConstants.INVALID_PARAMETER.getMsg()
            );
        }
    }
}
