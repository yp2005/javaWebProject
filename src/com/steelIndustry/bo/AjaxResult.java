package com.steelIndustry.bo;

public class AjaxResult {
    private int erroCode;// 错误码2000：成功，3000：失败，4000：未登录或者登陆超时，5000：没有权限
    private String erroMsg;// 错误信息
    private Object result;// ajax请求结果

    public int getErroCode() {
        return erroCode;
    }

    public void setErroCode(int erroCode) {
        this.erroCode = erroCode;
    }

    public String getErroMsg() {
        return erroMsg;
    }

    public void setErroMsg(String erroMsg) {
        this.erroMsg = erroMsg;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }
}
