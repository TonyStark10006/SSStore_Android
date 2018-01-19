package com.tonystark10006.ssstore;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;
import android.support.v7.widget.Toolbar;

public class MainActivity extends AppCompatActivity {

    Toolbar toolbar;
    private long exitTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences preferences = getSharedPreferences("token", Context.MODE_PRIVATE);
        String token = preferences.getString("tokenValue", "");
        if (token == "") {
            setContentView(R.layout.activity_main);
            //startActivity(new Intent(MainActivity.this, LoginActivity.class));
            Intent intent = new Intent();
            intent.setClass(MainActivity.this, LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            finish();
        } else {
            setContentView(R.layout.activity_main);
        }
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
        //menu.add(Menu.NONE, Menu.FIRST + 3, 4, "嘻嘻哈哈");
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_account:
                startActivity(new Intent(MainActivity.this, MyAccountActivity.class));
                break;
            case R.id.menu_aboutus:
                /*item.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {*/
                        startActivity(new Intent(MainActivity.this, AboutActivity.class));
                        /*return false;
                    }
                });*/
                break;
            case R.id.menu_setting:
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
                break;
            case R.id.menu_exit:
                finish();
                break;
            case Menu.FIRST + 3:
                Toast.makeText(this.getApplication(), "嘎嘎", Toast.LENGTH_LONG).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }


    //处理在主界面双击返回键退出程序
    @Override
    public void onBackPressed()
    {
        if (System.currentTimeMillis() - exitTime > 2000) {
            Toast.makeText(getApplicationContext(), "再按一次返回键退出程序",
                    Toast.LENGTH_SHORT).show();
            exitTime = System.currentTimeMillis();
        } else {
            //super.onBackPressed();
            finish();
        }
    }


    //定义一个内部类,实现View.OnClickListener接口,并重写onClick()方法
    /*class BtnClickListener implements View.OnClickListener
    {
        @Override
        public void onClick(View v) {
            Toast.makeText(getApplicationContext(), "按钮被点击了", Toast.LENGTH_SHORT).show();
        }
    }*/
}
