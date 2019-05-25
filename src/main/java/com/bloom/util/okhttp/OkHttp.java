package com.bloom.util.okhttp;

import com.alibaba.fastjson.JSONObject;
import okhttp3.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class OkHttp {
    private static Logger logger = LoggerFactory.getLogger(OkHttp.class);
    /**
     * OkHttpClient实例
     */
    private static OkHttpClient client = new OkHttpClient.Builder()
            .readTimeout(30, TimeUnit.SECONDS)//设置读取超时时间
            .writeTimeout(30, TimeUnit.SECONDS)//设置写的超时时间
            .connectTimeout(30, TimeUnit.SECONDS)//设置连接超时时间
            .build();

    /**
     * 获取okHttpClient实例
     * @return
     */
    public static OkHttpClient client() {
        return client;
    }

    /**
     * post 请求
     * @param url
     * @param json
     */
    public static JSONObject post(String url, String json) {
        logger.info("【POST】{} ===> \n {}", url, json);
        RequestBody requestBody = FormBody.create(MediaType.parse("application/json; charset=utf-8")
                , json);
        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();
        try {
            Response response = client.newCall(request).execute();
            JSONObject res = JSONObject.parseObject(response.body().string());
            logger.info("【RESULT】{}", res.toJSONString());
            response.close();
            return res;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * get 请求
     * @param url
     */
    public static JSONObject get(String url) {
        logger.info("【GET】{}", url);
        Request request = new Request.Builder()
                .url(url)
                .get()
                .build();
        try {
            Response response = client.newCall(request).execute();
            JSONObject res = JSONObject.parseObject(response.body().string());
            logger.info("【RESULT】{}", res.toJSONString());
            response.close();
            return res;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
