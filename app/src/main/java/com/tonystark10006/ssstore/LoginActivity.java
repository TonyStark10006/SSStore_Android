package com.tonystark10006.ssstore;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class LoginActivity extends AppCompatActivity
{
    EditText inputUsername;
    EditText inputPassword;
    Button login;
    Button logout;
    Toolbar toolbar;
    TextView toobarTitle;
    Handler myHandler = new Handler(Looper.getMainLooper());
    SharedPreferences preferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.preferences = getSharedPreferences("token", Context.MODE_PRIVATE);
        //String token = preferences.getString("tokenValue", "");
        //if (this.preferences.getString("tokenValue", "").equals("")) {
            setContentView(R.layout.activity_login);
            //startActivity(new Intent(MainActivity.this, LoginActivity.class));
            this.inputUsername = (EditText) findViewById(R.id.username);
            this.inputPassword = (EditText) findViewById(R.id.password);
            this.login = (Button) findViewById(R.id.login);
        /*} else {
            setContentView(R.layout.activity_myaccount);
            TextView username = (TextView)findViewById(R.id.myusername);
            username.setText(preferences.getString("username", ""));
            this.logout = (Button) findViewById(R.id.logout);
            this.logout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clearToken();
                }
            });
        }*/
        this.toolbar = (Toolbar) findViewById(R.id.my_toolbar);
        this.toobarTitle = (TextView) findViewById(R.id.toobarTitle);
        setSupportActionBar(this.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        //if (this.preferences.getString("tokenValue", "").equals("")) {
            this.toobarTitle.setText("登录");
        //} else {
        //    this.toobarTitle.setText("我的账户");
        //}
    }

    public void getMsg(View view) throws Exception
    {
        OkHttpClient client = new OkHttpClient.Builder().readTimeout(5, TimeUnit.SECONDS).build();
        //www.mofada.cn/Utils/guishudi.php?query=13560342474
        //post请求来获得数据
        //创建一个RequestBody，存放重要数据的键值对
        RequestBody body = new FormBody.Builder()
                .add("username", this.inputUsername.getText().toString())
                .add("password", this.inputPassword.getText().toString())
                .build();
        //创建一个请求对象，传入URL地址和相关数据的键值对的对象
        Request request = new Request.Builder().url(url).post(body).build();

        //创建一个能处理请求数据的操作类
        Call call = client.newCall(request);

        //使用异步任务的模式请求数据
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
                    myHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            //inputUsername.setText(phoneno);
                            //inputPassword.setText(carrier);
                            //inputUsername.setText(good);
                            if (good != "") {
                                preferences.edit().putString("tokenValue", good).apply();
                                preferences.edit().putString("username", inputUsername.getText().toString()).apply();
                                Toast.makeText(getApplication(), "登录成功", Toast.LENGTH_LONG).show();
                                //startActivity(new Intent(LoginActivity.this, MainActivity.class));
                                Intent intent = new Intent();
                                intent.setClass(LoginActivity.this, MainActivity.class);
                                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(intent);
                                finish();
                            } else {
                                Toast.makeText(getApplication(), "用户名或密码错误", Toast.LENGTH_LONG).show();
                            }
                        }
                    });
                    //}
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        });
    }

    /*SharedPreferences的四种操作模式:
    Context.MODE_PRIVATE
    Context.MODE_APPEND
    Context.MODE_WORLD_READABLE
    Context.MODE_WORLD_WRITEABLE
    Context.MODE_PRIVATE：为默认操作模式,代表该文件是私有数据,只能被应用本身访问,在该模式下,写入的内容会覆盖原文件的内容
    Context.MODE_APPEND：模式会检查文件是否存在,存在就往文件追加内容,否则就创建新文件.
    Context.MODE_WORLD_READABLE和Context.MODE_WORLD_WRITEABLE用来控制其他应用是否有权限读写该文件.
    MODE_WORLD_READABLE：表示当前文件可以被其他应用读取.
    MODE_WORLD_WRITEABLE：表示当前文件可以被其他应用写入.
    将数据保存至SharedPreferences:*/
    public void saveToken()
    {
        SharedPreferences preferences = getSharedPreferences("token", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        String tokenValue = "xixi";
        String age = "22";
        editor.putString("tokenValue", tokenValue);
        editor.putString("age", age);
        editor.apply();
        //editor.commit();

        /*从SharedPreferences获取数据:
        SharedPreferences preferences = getSharedPreferences("user", Context.MODE_PRIVATE);
        String name1 = preferences.getString("name", "defaultname");
        String age1 = preferences.getString("age", "0");*/
    }
}
