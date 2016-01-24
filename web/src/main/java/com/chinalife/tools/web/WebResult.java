package com.chinalife.tools.web;

import com.chinalife.tools.common.BizResultCodeEnum;

/**
 * controller返回result
 *
 * @author zhenggaojie 2015-5-15.
 */
public class WebResult<T> {

    /**
     * 执行结果
     */
    private String                resultCode;

    /**
     * 提示信息，会显示在页面上，标题
     */
    private String                message;

    /**
     * 成功标识
     */
    private boolean               success      = false;

    /**
     * 结果集
     */
    private T                     data;

    /**
     * 构造方法
     */
    public WebResult(){
    }

    /**
     * 新建一个对象
     *
     * @param resultCode 结果类型
     * @param message 显示在页面的信息
     */
    public WebResult(String resultCode, String message){
        this.resultCode = resultCode;
        this.message = message;
    }

    /**
     * 新建一个对象
     *
     * @param resultCode
     * @param message
     * @param success
     */
    public WebResult(String resultCode, String message, boolean success){
        this.resultCode = resultCode;
        this.message = message;
        this.success = success;
    }

    /**
     * 构造函数，转化BizResultCodeEnum对象
     *
     * @param codeEnum 错误码
     */
    public WebResult(BizResultCodeEnum codeEnum, boolean success){
        this(String.valueOf(codeEnum.getCodeNumber()), codeEnum.getDescription(), success);
    }

    /**
     * 构造函数，转化BizResultCodeEnum对象
     *
     * @param codeEnum 错误码
     */
    public WebResult(BizResultCodeEnum codeEnum){
        this(String.valueOf(codeEnum.getCodeNumber()), codeEnum.getDescription(), true);
    }

    /**
     * 构造函数，转化BizResultCodeEnum，同时attach data
     * 
     * @param codeEnum
     * @param data
     */
    public WebResult(BizResultCodeEnum codeEnum, T data){
        this(codeEnum);
        this.data = data;
    }

    /**
     * 转化BizResultCodeEnum对象
     *
     * @return
     */
    public static WebResult convertBizResultCode(BizResultCodeEnum codeEnum) {
        return new WebResult(String.valueOf(codeEnum.getCodeNumber()), codeEnum.getDescription());
    }

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean getSuccess() {
        return success;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
