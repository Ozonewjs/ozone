package com.ozone.mfls.utils;

/**
 * @ClassName HttpClientServiceUtils
 * @Description http工具类
 * @Author Ozone
 * @Date 2019/5/26 0026 16:17
 * @Version 1.0
 **/
public class HttpClientServiceUtils {
//    public static String insureResponsePost(String url, Map<String, String> ps) {
//        // 1. 创建HttpClient对象
//        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
//        // 2. 创建HttpPost对象
//        HttpPost post = new HttpPost(url);
//        // 3. 设置POST请求传递参数
//        List<NameValuePair> params = new ArrayList<NameValuePair>();
//        for (String key : ps.keySet())   { params.add(new BasicNameValuePair(key, ps.get(key)));}
//        HttpEntity resentity = null;
//        try {
//            UrlEncodedFormEntity entity = new UrlEncodedFormEntity(params);
//            post.setEntity(entity);
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }
//        // 4. 执行请求并处理响应
//        try {
//            CloseableHttpResponse response = httpClient.execute(post);
//            resentity = response.getEntity();
//            if (resentity != null){
//                System.out.println("响应内容：");
//                System.out.println(EntityUtils.toString(resentity));
//            }
//            response.close();
//            return EntityUtils.toString(resentity);
//        } catch (ClientProtocolException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            // 释放资源
//            try {
//                httpClient.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//       return null;
//    }
}
