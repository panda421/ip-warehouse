package com.example.proxy.common;

import java.util.HashMap;
import java.util.Map;

/**
 * 返回数据
 */
public class CommonResult extends HashMap<String, Object> {
    private static final long serialVersionUID = 1L;

    public CommonResult() {
        put("code", CodeEnum.SUCCESS_CODE.getCode());
        put("msg", CodeEnum.SUCCESS_CODE.getMsg());
        put("data", null);
    }

    public CommonResult(CodeEnum codeEnum) {
        put("code", codeEnum.getCode());
        put("msg", codeEnum.getMsg());
        put("data", null);
    }

    public static CommonResult error() {
        return error(CodeEnum.SERVER_INTERNAL_ERROR.getCode(), CodeEnum.SERVER_INTERNAL_ERROR.getMsg());
    }

    public static CommonResult error(String msg) {
        return error(CodeEnum.SERVER_INTERNAL_ERROR.getCode(), msg);
    }

    public static CommonResult error(int code, String msg) {
        CommonResult commonResult = new CommonResult();
        commonResult.put("code", code);
        commonResult.put("msg", msg);
        return commonResult;
    }

    public static CommonResult error(CodeEnum codeEnum) {
        CommonResult commonResult = new CommonResult(codeEnum);
        return commonResult;
    }

    public static CommonResult ok(String msg) {
        CommonResult commonResult = new CommonResult();
        commonResult.put("msg", msg);
        return commonResult;
    }

    public static CommonResult ok(Map<String, Object> map) {
        CommonResult commonResult = new CommonResult();
        commonResult.putAll(map);
        return commonResult;
    }

    public static CommonResult ok() {
        return new CommonResult();
    }

    public static CommonResult ok(CodeEnum codeEnum) {
        return new CommonResult(codeEnum);
    }

    public CommonResult data(Object data) {
        super.put("data", data);
        return this;
    }

    public CommonResult put(String key, Object value) {
        super.put(key, value);
        return this;
    }
}
