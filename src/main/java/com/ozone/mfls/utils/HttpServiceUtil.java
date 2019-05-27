package com.ozone.mfls.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
/**
 * @ClassName HttpServiceUtil
 * @Description http工具类
 * @Author Ozone
 * @Date 2019/5/26 0026 16:17
 * @Version 1.0
 **/
public class HttpServiceUtil {


    /**
     * 接口调用(post请求) 数据处理
     *
     * @param url
     *            请求路径 例如：http://127.0.0.1:8080/test/test
     * @param param
     *            请求参数 例如：{ "userName":"Lily", "password":"123456" }
     * @return 响应数据 例如：{ "resultId":"1" "resultMsg":"操作成功" }
     */
    public static String insureResponsePost(String url, String param) {
        PrintWriter out = null;
        InputStream is;
        BufferedReader br = null;
        String result = "";
        HttpURLConnection conn = null;
        StringBuffer strBuffer = new StringBuffer();
        try {
            URL realUrl = new URL(url);
            conn = (HttpURLConnection) realUrl.openConnection();
            // 设置通用的请求属性
            conn.setRequestMethod( "POST");
            conn.setConnectTimeout(20000);
            conn.setReadTimeout(300000);
            conn.setRequestProperty("Charset", "UTF-8");
//　　　　　　　 传输数据为json，如果为其他格式可以进行修改
            conn.setRequestProperty( "Content-Type", "application/json");
            conn.setRequestProperty( "Content-Encoding", "utf-8");
            // 发送POST请求必须设置如下两行
            conn.setDoOutput( true);
            conn.setDoInput( true);
            conn.setUseCaches( false);
            // 获取URLConnection对象对应的输出流
            out = new PrintWriter(conn.getOutputStream());
            // 发送请求参数
            out.print(param);
            // flush输出流的缓冲
            out.flush();
            is = conn.getInputStream();
            br = new BufferedReader( new InputStreamReader(is));
            String line ;
            while ((line=br.readLine())!= null) {
                strBuffer.append(line);
            }
            result = strBuffer.toString();
        } catch (Exception e) {
            System. out.println( "发送 POST 请求出现异常！" + e);
            e.printStackTrace();
        }
        // 使用finally块来关闭输出流、输入流
        finally {
            closeRes(out, br, conn);
        }
        return result;
    }

    private static void closeRes(PrintWriter out, BufferedReader br, HttpURLConnection conn) {
        try {
            if (out != null) {
                out.close();
            }
            if (br != null) {
                br.close();
            }
            if (conn != null) {
                conn.disconnect();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Http接口调用(get请求) 数据处理
     * @param url     请求地址  例如：http://127.0.0.1:8080/test/test?username=zhangsan$username=123456
     * @return String
     */
    public static String insureResponseBlockGet(String url) {
        InputStream is1 ;
        BufferedReader br1 = null;
        String result1 = "";
        HttpURLConnection conn1 = null;
        StringBuffer strBuffer1 = new StringBuffer();
        try {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            conn1 = (HttpURLConnection) realUrl.openConnection();
            // 设置通用的请求属性
            conn1.setRequestMethod("GET");
            conn1.setConnectTimeout(20000);
            conn1.setReadTimeout(300000);
//　　　　　　  传输数据为json，如果为其他格式可以进行修改
            conn1.setRequestProperty("Content-Type", "application/json");
            is1 = conn1.getInputStream();
            br1 = new BufferedReader( new InputStreamReader(is1));
            String line;
            while ((line=br1.readLine())!= null) {
                strBuffer1.append(line);
            }
            result1 = strBuffer1.toString();
        } catch (Exception e) {
            System.out.println( "发送 GET请求出现异常！" + e);
            e.printStackTrace();
        }
        // 使用finally块来关闭输出流、输入流
        finally {
            closeRes(null, br1, conn1);
        }
        return result1;
    }

}

