package com.cc.zuimei.adapter;

import android.app.Activity;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.cc.zuimei.info.ImageNewInfo;

import java.util.List;

import cc.com.zuimei.R;

public class NewAdapter extends RecyclerView.Adapter<NewAdapter.ViewHolder> {

        /** 上下文 */
        Activity context;

        /** 数据源 */
        List<ImageNewInfo> data;

        /** 控件 */
        LayoutInflater inflater;

    /**
     * 这里的data作为数据源从activity传入
     * @param activity
     * @param datas
     */
    public NewAdapter(Activity activity, List<ImageNewInfo> datas){
        this.context = activity;
        this.data = datas;

        //获取布局
        inflater = LayoutInflater.from(activity);
    }


    /**
     * 加载布局，相当于activity的onCreate方法
     * @param parent
     * @param viewType
     * @return
     */
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.imagenew_item, parent, false);
            return new ViewHolder(view);
            }

    /**
     * 绑定数据
     * @param viewHolder
     * @param position
     */
    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, int position) {
        //图片加载框架
        Glide
                .with(context)
                .load(data.get(position).getImg_url())
                .placeholder(R.mipmap.zwtp)
                .error(R.mipmap.zwtp)
                .into(viewHolder.imagenew_img);
    }

    /**
     * 数据源的内容大小
     * @return
     */
    @Override
    public int getItemCount() {
            return data.size();
            }

    /**
     * //自定义的ViewHolder，持有每个Item的的所有界面元素
     */
    public class ViewHolder extends RecyclerView.ViewHolder {

        /** 获取item的控件 */
        public ImageView imagenew_img;

        public ViewHolder(View rootView) {
            super(rootView);
            this.imagenew_img = rootView.findViewById(R.id.imagenew_img);

            //设置item的点击事件
            this.imagenew_img.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(Linster == null) return;
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