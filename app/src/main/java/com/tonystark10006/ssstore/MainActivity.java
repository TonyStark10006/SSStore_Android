package com.tonystark10006.ssstore;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.support.v7.widget.Toolbar;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
    EditText inputUsername;
    EditText inputPassword;
    Button login;
    Button about;
    Toolbar toolbar;
    Handler myHandler = new Handler(Looper.getMainLooper());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //绑定标签元素
        this.inputUsername = (EditText) findViewById(R.id.username);
        this.inputPassword = (EditText) findViewById(R.id.password);
        this.login = (Button) findViewById(R.id.login);
        //this.about = (Button) findViewById(R.id.about);
        this.toolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(this.toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        //this.toolbar.setTitle("欢迎来到SS小商店");
        //this.toolbar.inflateMenu(R.menu.base_toolbar_menu);
        //this.toolbar.setOnMenuItemClickListener();
        //绑定点击按钮动作
        /*login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                *//*Toast.makeText(getApplicationContext(), "输入了\n用户名："
                        + inputUsername.getText().toString() + "\n密码："
                        + inputPassword.getText().toString(), Toast.LENGTH_LONG).show();*//*
            }
        });*/

        /*about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, AboutActivity.class));
            }
        });*/
        //login.setOnClickListener(new BtnClickListener());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.base_toolbar_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_setting:
                Toast.makeText(this.getApplication(), "哈哈", Toast.LENGTH_LONG).show();
                break;
        }
        switch (item.getItemId()) {
            case R.id.menu_aboutus:
                /*item.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {*/
                        startActivity(new Intent(MainActivity.this, AboutActivity.class));
                        /*return false;
                    }
                });*/
                //Toast.makeText(this.getApplication(), "嘻嘻", Toast.LENGTH_LONG).show();
                break;
        }
        switch (item.getItemId()) {
            case R.id.menu_exit:
                //Toast.makeText(this.getApplication(), "嘎嘎", Toast.LENGTH_LONG).show();
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }



    //定义一个内部类,实现View.OnClickListener接口,并重写onClick()方法
    class BtnClickListener implements View.OnClickListener
    {
        @Override
        public void onClick(View v) {
            Toast.makeText(getApplicationContext(), "按钮被点击了", Toast.LENGTH_SHORT).show();
        }
    }

    public void getMsg(View view) throws Exception
    {
        OkHttpClient client = new OkHttpClient.Builder().readTimeout(5, TimeUnit.SECONDS).build();
        //www.mofada.cn/Utils/guishudi.php?query=13560342474
        Request request = new Request.Builder().url("http://192.168.31.223/laravel/public/test6").build();
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
                    JSONObject good1 = new JSONObject(good);
                    final String good2 = good1.getString("status");
                    JSONArray jsonArray = good1.getJSONArray("data");
                    Log.e("哈哈", Thread.currentThread().getName() + "   " + good);
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = (JSONObject) jsonArray.get(i);
                        final String phoneno = jsonObject.getString("phoneno");
                        final String carrier = jsonObject.getString("type");
                        myHandler.post(new Runnable() {
                            @Override
                            public void run() {
                                inputUsername.setText(phoneno);
                                inputPassword.setText(carrier);
                            }
                        });
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        });
    }
}
