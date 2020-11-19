package com.net.tools.futurelabnetconnecttools.common.exception;


import com.net.tools.futurelabnetconnecttools.common.req.AbstractResponse;
import com.net.tools.futurelabnetconnecttools.common.req.ResponseBaseCodeConstants;

/**
 * @author 衡钊清
 * @Classname ExceptionProcessUtils
 * @Description 发生异常后处理
 * @Date 2020/2/12 15:47
 */
public class ExceptionProcessUtils {

    /**
     * @Author 衡钊清
     * @Description TODO
     * @Date 2020/2/12 15:48
     * @Param
     **/
    public static void wrapperHandlerException(AbstractResponse response, Exception e){
        try {
            ExceptionConvertUtils.convertException2Biz(response,e);
        } catch (Exception ex){
            response.setCode(ResponseBaseCodeConstants.SERVICE_ERROR.getCode());
            response.setMsg(ResponseBaseCodeConstants.SERVICE_ERROR.getMsg());
        }
    }
}
