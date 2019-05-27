package com.ozone.mfls.server.imp;

import com.ozone.mfls.beans.LoginResult;

public interface LoginService {
    LoginResult login(String userName, String password);
    void logout();
}
