package com.cc.zuimei.adapter;

import android.app.Activity;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.util.LruCache;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.cc.zuimei.info.FragImageInfo;

import java.util.List;

import cc.com.zuimei.R;

public class FragTwoItemAdapter extends RecyclerView.Adapter<FragTwoItemAdapter.ViewHolder> {

    Activity context;

    List<FragImageInfo> data;

    LayoutInflater inflater;

    View view;

    public FragTwoItemAdapter(Activity activity, List<FragImageInfo> datas){
        this.context = activity;
        this.data = datas;
        inflater = LayoutInflater.from(activity);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.frag_two_frag_listview_item, parent, false);
        return new ViewHolder(view);
    }

    /** 标记是否正在滑动，如果为true，就暂停加载图片 */
    protected boolean isScrolling = false;

    /**
     * 赋值
     * @param scrolling
     */
    public void setScrolling(boolean scrolling) {
        isScrolling = scrolling;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, int position) {
        //图片加载框架
        Glide
                .with(context)
                .load(data.get(position).getImg_url())
                .asBitmap()
                .placeholder(R.mipmap.zwtp)
                .error(R.mipmap.zwtp)
                .into(viewHolder.frag_item_img);

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    /**
     * //自定义的ViewHolder，持有每个Item的的所有界面元素
     */
    public class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView frag_item_img;

        public ViewHolder(View rootView) {
            super(rootView);
            this.frag_item_img = rootView.findViewById(R.id.frag_item_img);

            frag_item_img.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Linster.textItemOnClick(v, getPosition());
                }
            });
        }
    }

    public ItemOnClickLinster Linster;

    public void setLinster(ItemOnClickLinster linster) {
        Linster = linster;
    }

    public interface ItemOnClickLinster{
        void textItemOnClick(View view, int position);
    }

}
