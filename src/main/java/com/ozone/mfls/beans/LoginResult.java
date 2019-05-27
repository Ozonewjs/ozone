package com.ozone.mfls.beans;

/**
 * @ClassName LoginResult
 * @Description TODO
 * @Author Ozone
 * @Date 2019/5/27 0027 21:50
 * @Version 1.0
 **/
public class LoginResult {
    private boolean isLogin = false;
    private String result;

    public boolean isLogin() {
        return isLogin;
    }

    public void setLogin(boolean login) {
        isLogin = login;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
