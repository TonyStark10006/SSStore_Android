package com.tonystark10006.ssstore;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.tonystark10006.ssstore.models.Data;

import java.util.ArrayList;

public class ConnectorActivity extends AppCompatActivity {
    Toolbar toolbar;
    TextView toobarTitle;
    Context context;
    FrameLayout nodeListZone;
    FragmentManager fragmentManager = null;
    ArrayList<Data> itemMsg = null;

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

        //处理节点信息列表
        context = ConnectorActivity.this;
        fragmentManager = getFragmentManager();
        nodeListZone = (FrameLayout) findViewById(R.id.nodeListContent);
        itemMsg = new ArrayList<Data>();
        for (int i = 1; i <= 20; i++) {
            Data data = new Data("节点" + i, "节点" + i +"信息");
            itemMsg.add(data);
        }
        nodeListFragment nlFragment = new nodeListFragment(fragmentManager, itemMsg);
        FragmentTransaction ft = fragmentManager.beginTransaction();
        ft.replace(R.id.nodeListContent, nlFragment);
        ft.commit();
    }

}
