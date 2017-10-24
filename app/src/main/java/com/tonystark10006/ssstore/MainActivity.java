package com.tonystark10006.ssstore;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    EditText inputUsername;
    EditText inputPassword;
    Button login;
    Button about;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //绑定标签元素
        this.inputUsername = (EditText)findViewById(R.id.username);
        this.inputPassword = (EditText)findViewById(R.id.password);
        this.login = (Button)findViewById(R.id.login);
        this.about = (Button)findViewById(R.id.about);

        //绑定点击按钮动作
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "输入了\n用户名："
                        + inputUsername.getText().toString() + "\n密码："
                        + inputPassword.getText().toString(), Toast.LENGTH_LONG).show();
            }
        });

        about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, AboutActivity.class));
            }
        });
        //login.setOnClickListener(new BtnClickListener());
    }



    //定义一个内部类,实现View.OnClickListener接口,并重写onClick()方法
    class BtnClickListener implements View.OnClickListener
    {
        @Override
        public void onClick(View v) {
            Toast.makeText(getApplicationContext(), "按钮被点击了", Toast.LENGTH_SHORT).show();
        }
    }

    private void updateView()
    {

    }

    public void getMsg() throws Exception
    {
        URL url = new URL("www.baidu.com");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
    }
}
