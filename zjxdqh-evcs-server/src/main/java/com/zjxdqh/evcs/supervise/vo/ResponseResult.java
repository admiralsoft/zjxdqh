package com.zjxdqh.evcs.supervise.vo;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

/**
 * @author Yorking
 * @date 2019/05/07
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY, getterVisibility = JsonAutoDetect.Visibility.NONE, isGetterVisibility = JsonAutoDetect.Visibility.NONE)
public class  ResponseResult<T> extends BaseResult<T> {

    public static final int RET_OK = 0;
    public static final int RET_BUSY = -1;
    public static final int RET_SIGN_ERROR = 4001;
    public static final int RET_TOKEN_ERROR = 4002;
    public static final int RET_PARAM_ERROR = 4003;
    public static final int RET_BUZZ_ERROR = 4004;
    public static final int RET_ERROR = 500;


    private String Msg;
    private int Ret;



    public static ResponseResult success(Object data) {
        ResponseResult result = new ResponseResult();
        result.setData(data);
        result.setRet(RET_OK);
        result.setMsg("请求成功");
        return result;
    }

    public static ResponseResult error(int ret) {
        ResponseResult result = new ResponseResult();
        result.setRet(ret);
        result.setMsg(ret + "错误");
        return result;
    }


    public static ResponseResult error(int ret, String msg) {
        return error(ret).msg(msg);
    }

    public ResponseResult msg(String msg) {
        this.setMsg(msg);
        return this;
    }
}
