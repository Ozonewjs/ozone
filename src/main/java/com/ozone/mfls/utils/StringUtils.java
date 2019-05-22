package com.ozone.mfls.utils;

public  class StringUtils {
    public static boolean isEmpty(String str)
    {
        boolean isEmpty = false;
        if (null == str || "".equals(str))
        {
            isEmpty = true;
        }
        return isEmpty;
    }
}
