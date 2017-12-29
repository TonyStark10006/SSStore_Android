package com.tonystark10006.ssstore.models;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.tonystark10006.ssstore.R;

import java.util.List;

public class NodeListAdapter extends BaseAdapter{
    private List<Data> nodeList;
    private Context context;

    public NodeListAdapter(List<Data> nodeList, Context context) {
        this.nodeList = nodeList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return nodeList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.node_list_item, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.txtItemTitle = (TextView) convertView.findViewById(R.layout.node_list_item);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.txtItemTitle.setText(nodeList.get(position).getNewTitle());
        return convertView;
    }

    private class ViewHolder {
        TextView txtItemTitle;
    }



}
