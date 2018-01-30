package com.tonystark10006.ssstore;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.tonystark10006.ssstore.models.Data;

import java.util.ArrayList;

public class MyAccountActivity extends AppCompatActivity {
    Button logout;
    Toolbar toolbar;
    TextView toobarTitle;
    SharedPreferences preferences;
    /*Context context;
    FrameLayout nodeListZone;
    FragmentManager fragmentManager = null;
    ArrayList<Data> itemMsg = null;*/

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myaccount);
        TextView username = (TextView) findViewById(R.id.myusername);
        this.preferences = getSharedPreferences("token", Context.MODE_PRIVATE);
        username.setText(preferences.getString("username", ""));
        this.logout = (Button) findViewById(R.id.logout);
        this.logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearToken();
            }
        });
        this.toolbar = (Toolbar) findViewById(R.id.my_toolbar);
        this.toobarTitle = (TextView) findViewById(R.id.toobarTitle);
        setSupportActionBar(this.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        this.toobarTitle.setText("我的账户");

        /*//处理节点信息列表
        context = MyAccountActivity.this;
        fragmentManager = getFragmentManager();
        nodeListZone = (FrameLayout) findViewById(R.id.nodeListContent);
        itemMsg = new ArrayList<Data>();
        for (int i = 1; i <= 20; i++) {
            Data data = new Data("新闻标题" + i, i + "~新闻内容~~~~~~~~");
            itemMsg.add(data);
        }
        //Bundle bundle = new Bundle();
        //bundle.putSerializable("data", itemMsg);
        //bundle.putSerializable("good", fragmentManager);
        //bundle.putSerializable("fragmentManager", fragmentManager);
        nodeListFragment nlFragment = new nodeListFragment(fragmentManager, itemMsg);
        //nlFragment.setArguments(bundle);
        FragmentTransaction ft = fragmentManager.beginTransaction();
        ft.replace(R.id.nodeListContent, nlFragment);
        ft.commit();*/
    }

    public void clearToken()
    {
        this.preferences.edit().remove("tokenValue").apply();
        Toast.makeText(getApplication(), "退出成功", Toast.LENGTH_LONG).show();
        Intent intent = new Intent();
        intent.setClass(MyAccountActivity.this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
        //startActivity(new Intent(LoginActivity.this, MainActivity.class));
    }
}
