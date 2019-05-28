package com.ozone.mfls.utils;

public class Test {
    public static void main(String [] args){
        String key = "cvicse";
        try {
            String md5res = MD5Utils.md5("150582",key);
        }catch (Exception e){
            e.printStackTrace();
        }
        try {
            boolean a  = MD5Utils.verify("150582",key,"843c609c65f7e4d854c0dd514afa2e72");
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
