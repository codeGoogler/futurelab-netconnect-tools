package com.net.tools.futurelabnetconnecttools.common.req;

/**
 * @author 衡钊清
 * @Classname CommonResponseFactory
 * @Description 构建数据响应工厂
 * @Date 2020/2/13 10:32
 */
public class CommonResponseFactory<T> {


    /**
     * @return
     * @Author 衡钊清
     * @Description 成功，默认响应码
     * @Date 2020/2/13 10:43
     * @Param
     **/
    public CommonResponse success() {
        return build(ResponseBaseCodeConstants.SUCCESS.getCode(), ResponseBaseCodeConstants.SUCCESS.getMsg());
    }

    /**
     * @return
     * @Author 衡钊清
     * @Description 默认响应码及返回数据
     * @Date 2020/2/13 10:43
     * @Param
     **/
    public CommonResponse success(T data) {
        return build(ResponseBaseCodeConstants.SUCCESS.getCode(), ResponseBaseCodeConstants.SUCCESS.getMsg(), data);
    }

    /**
     * @return
     * @Author 衡钊清
     * @Description 自定义
     * @Date 2020/2/13 10:44
     * @Param
     **/
    public CommonResponse success(String msg, T data) {
        return build(ResponseBaseCodeConstants.SUCCESS.getCode(), msg, data);
    }

    public CommonResponse success(String code, String msg, T data) {
        return build(ResponseBaseCodeConstants.SUCCESS.getCode(), msg, data);
    }

    public CommonResponse error() {
        return build(ResponseBaseCodeConstants.SERVICE_ERROR.getCode(),
                ResponseBaseCodeConstants.SERVICE_ERROR.getMsg());
    }

    public CommonResponse error(String msg) {
        return build(ResponseBaseCodeConstants.SERVICE_ERROR.getCode(), msg);
    }

    /**
     * @return
     * @Author 衡钊清
     * @Description 构建CommonResponse
     * @Date 2020/2/13 10:41
     * @Param
     **/
    public CommonResponse<T> build(String code, String msg) {
        return this.build(code, msg, null);
    }

    /**
     * @return
     * @Author 衡钊清
     * @Description 构建CommonResponse
     * @Date 2020/2/13 10:34
     * @Param code
     **/
    public CommonResponse<T> build(String code, String msg, T data) {
        return new CommonResponse(code, msg, data);
    }


    /**
     * 获取工程实例
     *
     * @return
     */
    public static CommonResponseFactory getInstance() {
        return InnerResutl.INSTANCE;
    }

    /**
     * 私有构造参数，工厂采用单例
     */
    private CommonResponseFactory() {
    }

    static class InnerResutl {
        private static CommonResponseFactory INSTANCE = new CommonResponseFactory();
    }

}
