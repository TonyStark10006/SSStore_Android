package com.tonystark10006.ssstore.utils;


import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class HttpUtils {
    String url;
    Map<String, String> parm;
    private final OkHttpClient client = new OkHttpClient();

    Handler handler = new Handler(Looper.getMainLooper());

    public void run(View view) throws Exception
    {
        OkHttpClient client = new OkHttpClient.Builder().readTimeout(5, TimeUnit.SECONDS).build();
        //www.mofada.cn/Utils/guishudi.php?query=13560342474"http://192.168.31.223/laravel/public/api/test6?token=nima"
        Request request = new Request.Builder().url(url).build();
        Call call = client.newCall(request);

        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                //失败
                Log.e("TAG",Thread.currentThread().getName() + "结果  " + e.toString());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                //成功  子线程
                //main thread1
                //Log.e("TAG","结果  " + response.body().string().getClass().toString());
                try {
                    final String good = response.body().string();
                    /*JSONObject good1 = new JSONObject(good);
                    final String good2 = good1.getString("status");
                    JSONArray jsonArray = good1.getJSONArray("data");
                    Log.e("哈哈", Thread.currentThread().getName() + "   " + good);
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = (JSONObject) jsonArray.get(i);
                        final String phoneno = jsonObject.getString("phoneno");
                        final String carrier = jsonObject.getString("type");*/
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            //inputUsername.setText(phoneno);
                            //inputPassword.setText(carrier);
                            //inputUsername.setText(good);
                            /*preferences.edit().putString("tokenValue", good).apply();
                            Toast.makeText(getApplication(), "登录成功", Toast.LENGTH_LONG).show();
                            startActivity(new Intent(LoginActivity.this, MainActivity.class));*/
                        }
                    });
                    //}
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
