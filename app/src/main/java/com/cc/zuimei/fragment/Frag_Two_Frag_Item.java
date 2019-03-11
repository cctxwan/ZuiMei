package com.cc.zuimei.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;

import com.cc.zuimei.adapter.FragTwoItemAdapter;
import com.cc.zuimei.info.FragImageInfo;

import java.util.ArrayList;
import java.util.List;

import cc.com.zuimei.R;

public class Frag_Two_Frag_Item extends Fragment {

    String item_title="默认";

    public static String BUNDLE_TITLE = "title";

    /**
     * 传值
     * @param item_title
     * @return
     */
    public static Frag_Two_Frag_Item getInstance(String item_title){
        Bundle bundle = new Bundle();
        bundle.putString(BUNDLE_TITLE, item_title);
        Frag_Two_Frag_Item frag_two_frag_item = new Frag_Two_Frag_Item();
        frag_two_frag_item.setArguments(bundle);
        return frag_two_frag_item;
    }

    View view;

    RecyclerView frag_item_listView;

    ImageView img_itemtwofrag_load;

    //获取最后一个可见view的坐标
    int lastItemPosition;
    //获取第一个可见view的坐标
    int firstItemPosition;


    public static FragTwoItemAdapter adapter;

    List<FragImageInfo> datas = new ArrayList<>();

    /**
     * handler
     * 通过线程获取数据并加载adapter
     */
    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            img_itemtwofrag_load.clearAnimation();
            img_itemtwofrag_load.setVisibility(View.GONE);
            //设置RecyclerView的数据加载
            frag_item_listView.setLayoutManager(new LinearLayoutManager(getActivity()));
            adapter = new FragTwoItemAdapter(getActivity(), datas);
            frag_item_listView.setAdapter(adapter);

            adapter.setLinster(new FragTwoItemAdapter.ItemOnClickLinster(){
                @Override
                public void textItemOnClick(View view, int position) {
                    Log.i("getActivity()", "点击事件");
                }
            });

        }
    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_two_item, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        initView();
        initData();
    }

    private void initData() {
        img_itemtwofrag_load.setVisibility(View.VISIBLE);
        openA(getActivity(), img_itemtwofrag_load);
        handler.post(getDatas);


        datas.add(new FragImageInfo("1",R.mipmap.two_frag_00));
        datas.add(new FragImageInfo("2",R.mipmap.two_frag_01));
        datas.add(new FragImageInfo("3",R.mipmap.two_frag_02));
        datas.add(new FragImageInfo("4",R.mipmap.two_frag_03));
        datas.add(new FragImageInfo("5",R.mipmap.two_frag_04));
        datas.add(new FragImageInfo("6",R.mipmap.two_frag_05));
        datas.add(new FragImageInfo("7",R.mipmap.two_frag_06));
        datas.add(new FragImageInfo("8",R.mipmap.two_frag_07));
        datas.add(new FragImageInfo("9",R.mipmap.two_frag_08));
        datas.add(new FragImageInfo("10",R.mipmap.two_frag_09));
        datas.add(new FragImageInfo("11",R.mipmap.two_frag_10));
        datas.add(new FragImageInfo("12",R.mipmap.two_frag_11));
        datas.add(new FragImageInfo("13",R.mipmap.two_frag_12));
        datas.add(new FragImageInfo("14",R.mipmap.two_frag_13));
        datas.add(new FragImageInfo("15",R.mipmap.two_frag_14));
        datas.add(new FragImageInfo("16",R.mipmap.two_frag_15));
        datas.add(new FragImageInfo("17",R.mipmap.two_frag_16));
        datas.add(new FragImageInfo("18",R.mipmap.two_frag_17));

        frag_item_listView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if(newState == RecyclerView.SCROLL_STATE_IDLE ){
                    Log.i("getActivity()", "这里应该是一动不动的");
                    //这里滑动停止，开始加载可见项
                    System.out.println(firstItemPosition + "   " + lastItemPosition);
                    adapter.setScrolling(false);
                    adapter.notifyDataSetChanged();
                }else if(newState == RecyclerView.SCROLL_STATE_DRAGGING){
                    Log.i("getActivity()", "这里应该是开始滑动");
                    //这里做处理（停止加载一切事情）
                    adapter.setScrolling(true);
                }else if(newState == RecyclerView.SCROLL_STATE_SETTLING){
                    Log.i("getActivity()", "这里应该是手指离开屏幕的事件");
                    //

                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
                //判断是当前layoutManager是否为LinearLayoutManager
                // 只有LinearLayoutManager才有查找第一个和最后一个可见view位置的方法
                if (layoutManager instanceof LinearLayoutManager) {
                    LinearLayoutManager linearManager = (LinearLayoutManager) layoutManager;
                    //获取最后一个可见view的位置
                    lastItemPosition = linearManager.findLastVisibleItemPosition();
                    //获取第一个可见view的位置
                    firstItemPosition = linearManager.findFirstVisibleItemPosition();
                }
            }
        });

        //如果确定每个item的内容不会改变RecyclerView的大小，设置这个选项可以提高性能
        frag_item_listView.setHasFixedSize(true);
        frag_item_listView.setItemAnimator(new DefaultItemAnimator());
    }


    /**
     * 获取数据
     */
    Runnable getDatas = new Runnable() {
        @Override
        public void run() {
            switch (item_title) {
                case "全部":
                    getData();
                    break;
                case "自然":
                    getData();
                    break;
                case "植物":
                    getData();
                    break;
                case "城市":
                    getData();
                    break;
                case "人文":
                    getData();
                    break;
                case "体育":
                    getData();
                    break;
                case "明星":
                    getData();
                    break;
                case "动物":
                    getData();
                    break;
                case "汽车":
                    getData();
                    break;
                case "文艺":
                    getData();
                    break;
                default :
                    break;
            }
        }
    };

    private void getData(){
        //模仿网络请求返回的参数
        Message message = handler.obtainMessage();
        handler.sendMessage(message);
    }

    private void initView() {
        Bundle bundle = getArguments();
        if(bundle != null){
            item_title = bundle.getString(BUNDLE_TITLE);
        }
        frag_item_listView = view.findViewById(R.id.frag_item_listView);
        img_itemtwofrag_load = view.findViewById(R.id.img_itemtwofrag_load);
    }

    /**
     * 开启一个动画
     * @param img
     */
    public static void openA(Activity activity, ImageView img){
        //加载loading动画
        rotateAnimation = AnimationUtils.loadAnimation(activity, R.anim.loading);
        LinearInterpolator interpolator = new LinearInterpolator();
        rotateAnimation.setInterpolator(interpolator);
        img.startAnimation(rotateAnimation);
    }

    private static Animation rotateAnimation;

}
