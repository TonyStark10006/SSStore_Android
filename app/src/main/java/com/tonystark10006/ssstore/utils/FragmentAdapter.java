package com.tonystark10006.ssstore.utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.tonystark10006.ssstore.models.Data;
import com.tonystark10006.ssstore.R;

import java.util.List;


public class FragmentAdapter extends BaseAdapter {
    private List<Data> mData;
    private Context mContext;

    public FragmentAdapter(List<Data> mData, Context mContext) {
        this.mData = mData;
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return mData.size();
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
        if(convertView == null){
            convertView = LayoutInflater.from(mContext).inflate(R.layout.node_list_item_title, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.nodeListItemTitle = (TextView) convertView.findViewById(R.id.node_list_item_title);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.nodeListItemTitle.setText(mData.get(position).getNewTitle());
        return convertView;
    }

    private class ViewHolder{
        TextView nodeListItemTitle;
    }
}
