package com.cc.zuimei.test.asynctask;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import cc.com.zuimei.R;

/**
 * 实现新闻界面适配器
 */
public class NewAdapter extends BaseAdapter {

    Activity mactivity;

    List<NewInfo> data;

    ListView mlistview;

    //加载item布局
    LayoutInflater inflater;

    public NewAdapter(Activity activity, List<NewInfo> datas, ListView listView) {
        this.mactivity = activity;
        this.data = datas;
        this.mlistview = listView;

        inflater = LayoutInflater.from(mactivity);
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //判断convertView为空
        ViewHolder viewHolder = null;
        if(convertView == null){
            viewHolder = new ViewHolder();
            convertView = inflater.inflate(R.layout.new_item, null);
            viewHolder.imageView = convertView.findViewById(R.id.new_img);
            viewHolder.textView = convertView.findViewById(R.id.new_txt);
            //设置tag
            convertView.setTag(viewHolder);
        }else{
            //直接取出tag
            viewHolder = (ViewHolder) convertView.getTag();
        }
        Glide
                .with(mactivity)
                .load(data.get(position).getResult().getData().get(position).getThumbnail_pic_s())
                .asBitmap()
                .placeholder(R.mipmap.zwtp)
                .error(R.mipmap.zwtp)
                .into(viewHolder.imageView);
        viewHolder.textView.setText(data.get(position).getResult().getData().get(position).getTitle());

        return convertView;
    }

    //文艺的ViewHolder
    class ViewHolder{
        public ImageView imageView;
        public TextView textView;
    }

}
