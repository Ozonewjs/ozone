package com.ozone.mfls.myexception;

/**
 * @ClassName FastRuntimeException
 * @Author Ozone
 * @Description TODO
 * @Date 2019/5/31 15:29
 * @Version 1.0
 **/
public class FastRuntimeException extends Exception{
    private int code;
    private String msg;
    public FastRuntimeException(int code,String msg){
        super(msg);
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
