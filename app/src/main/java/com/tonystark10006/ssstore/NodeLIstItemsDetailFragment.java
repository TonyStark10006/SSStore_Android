package com.tonystark10006.ssstore;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class NodeLIstItemsDetailFragment extends Fragment {
    public NodeLIstItemsDetailFragment()
    {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState)
    {
        View view = inflater.inflate(R.layout.node_list_item_detail, container, false);
        TextView nodeListItemDetail = (TextView) view.findViewById(R.id.node_list_item_detail);
        nodeListItemDetail.setText(getArguments().getString("content"));
        return view;
    }
}
