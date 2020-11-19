package com.net.tools.futurelabnetconnecttools.common.exception;


import com.net.tools.futurelabnetconnecttools.common.exception.support.ParamsValidateException;
import com.net.tools.futurelabnetconnecttools.common.req.AbstractResponse;

/**
 * @author 衡钊清
 * @Classname ExceptionConvertUtils
 * @Description 异常转换类
 * @Date 2020/2/12 15:48
 */
public class ExceptionConvertUtils {

    /**
     * 异常类转化和返回数据封装
     * @param response
     * @param e
     * @return
     * @throws Exception
     */
    public static AbstractResponse convertException2Biz(AbstractResponse response, Exception e) throws Exception {
        // 参数异常
        if (e instanceof ParamsValidateException){
            response.setCode(((ParamsValidateException) e).getCode());
            response.setMsg(((ParamsValidateException) e).getMsg());
        } else if (e instanceof BaseException) {
            response.setCode(((BaseException)e).getCode());
            response.setCode(((BaseException)e).getMsg());
        }else {
            // 该异常服务端处理不了，扔给服务调用者
            throw e;
        }
        return response;
    }
}
