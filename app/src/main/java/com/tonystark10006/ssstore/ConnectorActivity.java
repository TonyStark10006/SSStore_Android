package com.tonystark10006.ssstore;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.tonystark10006.ssstore.models.Data;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class ConnectorActivity extends AppCompatActivity {
    Toolbar toolbar;
    TextView toobarTitle;
    Context context;
    FrameLayout nodeListZone;
    FragmentManager fragmentManager = null;
    ArrayList<Data> itemMsg = null;

    SharedPreferences SPToken;
    SharedPreferences SPNodeListMsg;
    Handler myHandler;

    protected void onCreate(Bundle saveInstanceState)
    {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_connector);
        this.toolbar = (Toolbar) findViewById(R.id.node_list_toolbar);
        this.toobarTitle = (TextView) findViewById(R.id.node_list_title);
        setSupportActionBar(this.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        this.toobarTitle.setText("选择节点");

        this.SPToken = getSharedPreferences("token", Context.MODE_PRIVATE);
        this.SPNodeListMsg = getSharedPreferences("nodeListMsg", Context.MODE_PRIVATE);
        this.myHandler = new Handler(Looper.getMainLooper());

        this.getNodeListMsg();

        //this.updateNodeListView();
        //JSONArray nodeListMsgJson = JSON.parseArray(SPNodeListMsg.getString("data", ""));
        /*JSONObject nima = JSON.parseObject(SPNodeListMsg.getString("data", ""));
        JSONArray nodeListMsgJson = JSON.parseArray(nima.getString("data"));
        Log.e("nima", nodeListMsgJson.getString(1));

        //处理节点信息列表
        context = ConnectorActivity.this;
        fragmentManager = getFragmentManager();
        nodeListZone = (FrameLayout) findViewById(R.id.nodeListContent);
        itemMsg = new ArrayList<Data>();
        //for (int i = 1; i <= 20; i++) {
        //    Data data = new Data("节点" + i, "节点" + i +"信息");
        //    itemMsg.add(data);
        //}
        for (int i = 0; i < nodeListMsgJson.size(); i++) {
            JSONObject nodeMsg = JSON.parseObject(nodeListMsgJson.getString(i));
            Data data = new Data(nodeMsg.getString("node_name"), nodeMsg.getString("email"));
            itemMsg.add(data);
        }
        nodeListFragment nlFragment = new nodeListFragment(fragmentManager, itemMsg);
        FragmentTransaction ft = fragmentManager.beginTransaction();
        ft.replace(R.id.nodeListContent, nlFragment);
        ft.commit();*/
    }

    public void getNodeListMsg()
    {
        Config config1 = new Config();
        OkHttpClient client = new OkHttpClient.Builder().readTimeout(5, TimeUnit.SECONDS).build();
        String url = config1.GET_NODE_LIST_MSG_URL;
        //post请求来获得数据
        //创建一个RequestBody，存放重要数据的键值对
        RequestBody body = new FormBody.Builder()
                .add("token", SPToken.getString("tokenValue", ""))
                .add("origin", "xixi")
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
                try {
                    final String good = response.body().string();
                    myHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            JSONObject jsonObject = JSON.parseObject(good);
                            if (jsonObject.getString("status").equals("success")) {
                                //SPNodeListMsg.edit().putString("data", good).apply();
                                //Toast.makeText(getApplication(), good, Toast.LENGTH_LONG).show();

                                //JSONObject nima = JSON.parseObject(SPNodeListMsg.getString("data", ""));
                                JSONArray nodeListMsgJson = JSON.parseArray(jsonObject.getString("data"));
                                //Log.e("nima", nodeListMsgJson.getString(1));

                                //处理节点信息列表
                                context = ConnectorActivity.this;
                                fragmentManager = getFragmentManager();
                                nodeListZone = (FrameLayout) findViewById(R.id.nodeListContent);
                                itemMsg = new ArrayList<Data>();
                                String enable;
                                String expireTime;
                                SimpleDateFormat time = new SimpleDateFormat("yyyy-MM-dd-HH-mm:ss");
                                for (int i = 0; i < nodeListMsgJson.size(); i++) {
                                    JSONObject nodeMsg = JSON.parseObject(nodeListMsgJson.getString(i));

                                    if (nodeMsg.getString("enable").equals("1")) {
                                        enable = "可用";
                                    } else {
                                        enable = "不可用";
                                    }

                                    expireTime = time.format(
                                            Integer.parseInt(nodeMsg.getString("expire_time")) * 1000L);

                                    Data data = new Data(
                                            nodeMsg.getString("node_name"),
                                            "节点名称：" + nodeMsg.getString("node_name")
                                                    + "\n"
                                                    + "用户名：" + nodeMsg.getString("user_name")
                                                    + "\n"
                                                    + "邮件地址：" + nodeMsg.getString("email")
                                                    + "\n"
                                                    + "密码：" + nodeMsg.getString("passwd")
                                                    + "\n"
                                                    + "端口号：" + nodeMsg.getString("port")
                                                    + "\n"
                                                    + "协议：" + nodeMsg.getString("protocol")
                                                    + "\n"
                                                    + "混淆方式：" + nodeMsg.getString("obfs")
                                                    + "\n"
                                                    + "加密方法：" + nodeMsg.getString("method")
                                                    + "\n"
                                                    + "是否可用：" + enable
                                                    + "\n"
                                                    + "到期时间：" + expireTime);
                                    itemMsg.add(data);
                                }
                                nodeListFragment nlFragment = new nodeListFragment(fragmentManager, itemMsg);
                                FragmentTransaction ft = fragmentManager.beginTransaction();
                                ft.replace(R.id.nodeListContent, nlFragment);
                                ft.commit();
                            } else {
                                Toast.makeText(getApplication(), jsonObject.getString("msg"), Toast.LENGTH_LONG).show();
                            }
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        });

        //使用同步模式请求数据
        /*try {
            Response response = call.execute();
            final String good = response.body().string();
            JSONObject jsonObject = JSON.parseObject(good);
            if (jsonObject.getString("status").equals("success")) {
                SPNodeListMsg.edit().putString("data", good).apply();
                //Log.e("good", good);
            } else {
                Toast.makeText(getApplication(), jsonObject.getString("msg"), Toast.LENGTH_LONG).show();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }*/
    }

    public void updateNodeListView()
    {
        JSONObject nima = JSON.parseObject(SPNodeListMsg.getString("data", ""));
        JSONArray nodeListMsgJson = JSON.parseArray(nima.getString("data"));
        Log.e("nima", nodeListMsgJson.getString(1));
        //处理节点信息列表
        context = ConnectorActivity.this;
        fragmentManager = getFragmentManager();
        nodeListZone = (FrameLayout) findViewById(R.id.nodeListContent);
        itemMsg = new ArrayList<Data>();
        /*for (int i = 1; i <= 20; i++) {
            Data data = new Data("节点" + i, "节点" + i +"信息");
            itemMsg.add(data);
        }*/
        for (int i = 0; i < nodeListMsgJson.size(); i++) {
            JSONObject nodeMsg = JSON.parseObject(nodeListMsgJson.getString(i));
            Data data = new Data(nodeMsg.getString("node_name"), nodeMsg.getString("email"));
            itemMsg.add(data);
        }
        nodeListFragment nlFragment = new nodeListFragment(fragmentManager, itemMsg);
        FragmentTransaction ft = fragmentManager.beginTransaction();
        ft.replace(R.id.nodeListContent, nlFragment);
        ft.commit();
    }

}
