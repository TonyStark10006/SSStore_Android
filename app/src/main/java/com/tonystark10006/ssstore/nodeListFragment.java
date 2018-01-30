package com.tonystark10006.ssstore;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.tonystark10006.ssstore.models.Data;
import com.tonystark10006.ssstore.utils.FragmentAdapter;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by vofy on 2018/1/26.
 */

public class nodeListFragment extends Fragment implements AdapterView.OnItemClickListener {
    private FragmentManager fragmentManager;
    private ListView nodeList;
    private ArrayList<Data> nodeListItemsDetail;

    public nodeListFragment(FragmentManager fragmentManager, ArrayList<Data> data) {
        this.fragmentManager = fragmentManager;
        this.nodeListItemsDetail = data;
        //
    }

    /*@Override
    public void setArguments(Bundle arguments)
    {
        arguments.putStringArrayList("data");
    }*/
    /*@Override
    public void onCreate (Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        //this.fragmentManager = getFragmentManager();
        this.nodeListItemsDetail = (ArrayList<Data>) getArguments().getSerializable("data");
    }*/


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.node_list, container, false);
        nodeList = (ListView) view.findViewById(R.id.node_list);
        /*Iterator it1 = this.nodeListItemsDetail.iterator();
        while (it1.hasNext()) {
            System.out.println(it1.next());
        }*/
        FragmentAdapter fragmentAdapter = new FragmentAdapter(this.nodeListItemsDetail, getActivity());
        nodeList.setAdapter(fragmentAdapter);
        nodeList.setOnItemClickListener(this);
        return view;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id)
    {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        NodeLIstItemsDetailFragment nodeLIstItemsDetailFragment = new NodeLIstItemsDetailFragment();
        Bundle bundle = new Bundle();
        bundle.putString("content", this.nodeListItemsDetail.get(position).getNewContent());
        nodeLIstItemsDetailFragment.setArguments(bundle);

        //获取Activity的控件
        //TextView txt_title = (TextView) getActivity().findViewById(R.id.toobarTitle);
        //txt_title.setText(this.nodeListItemsDetail.get(position).getNewContent());

        fragmentTransaction.setCustomAnimations(R.anim.fragment_slide_left_enter, R.anim.fragment_slide_left_exit);
        fragmentTransaction.replace(R.id.nodeListContent, nodeLIstItemsDetailFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}
