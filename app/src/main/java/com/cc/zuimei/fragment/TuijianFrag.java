package com.cc.zuimei.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.cc.zuimei.adapter.HuoAdapter;
import com.cc.zuimei.adapter.NewAdapter;
import com.cc.zuimei.info.ImageHuoInfo;
import com.cc.zuimei.info.ImageNewInfo;
import com.cc.zuimei.myview.ScrollRecyclerView;
import com.cc.zuimei.systembar.StatusBarCompat;
import com.cc.zuimei.systembar.StatusBarUtil;
import com.cc.zuimei.utils.GetDate;
import com.stx.xhb.xbanner.XBanner;
import com.stx.xhb.xbanner.transformers.Transformer;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.ArrayList;
import java.util.List;

import cc.com.zuimei.BannerDetailActivity;
import cc.com.zuimei.R;

/**
 * 推荐的fragment
 */
public class TuijianFrag extends Fragment implements XBanner.XBannerAdapter, XBanner.OnItemClickListener {

    View view;

    /** 轮播图 */
    XBanner xBanner;

    /** 轮播图图片资源加载器 */
    List<Integer> imgesUrl = new ArrayList<>();

    /** 轮播图文字资源加载器 */
    List<String> textUrl = new ArrayList<>();

    ScrollRecyclerView frag_new_rv, frag_huo_rv;

    NewAdapter new_adapter;

    HuoAdapter huo_adapter;

    List<ImageNewInfo> new_datas = new ArrayList<>();
    List<ImageHuoInfo> huo_datas = new ArrayList<>();

    NestedScrollView nestedScrollView;

    Handler handler = new Handler();

    TextView tuijian_txt_date;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_tuijian, container, false);
        initView(view);
        return view;
    }


    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            addData();
            setAdapter();
            handler.post(getdate);
        }
    };

    Runnable getdate = new Runnable() {
        @Override
        public void run() {
            tuijian_txt_date.setText(GetDate.StringData());
            handler.postDelayed(getdate, 1000);
        }
    };

    private void initView(View view) {

        tuijian_txt_date = view.findViewById(R.id.tuijian_txt_date);
        nestedScrollView = view.findViewById(R.id.nestedScrollView);
        //轮播图
        xBanner = view.findViewById(R.id.frag_one_xbanner);


        frag_new_rv = view.findViewById(R.id.frag_new_rv);
        frag_huo_rv = view.findViewById(R.id.frag_huo_rv);
    }

    /**
     * 获取网络数据
     */
    private void addData() {
        frag_new_rv.setNestedScrollingEnabled(false);
        frag_huo_rv.setNestedScrollingEnabled(false);

        xBanner.setPageTransformer(Transformer.Accordion);
        xBanner.startAutoPlay();
        xBanner.setOnItemClickListener(this);

        //轮播图加载
        imgesUrl.add(R.mipmap.banner_00);
        imgesUrl.add(R.mipmap.banner_01);
        imgesUrl.add(R.mipmap.banner_02);
        imgesUrl.add(R.mipmap.banner_03);
        textUrl.add("纹理/简约");
        textUrl.add("纹理/简约");
        textUrl.add("纹理/简约");
        textUrl.add("纹理/简约");
        xBanner.setData(imgesUrl, textUrl);

        //最新图片加载
        new_datas.add(new ImageNewInfo("1",R.mipmap.huo_00));
        new_datas.add(new ImageNewInfo("2",R.mipmap.huo_01));
        new_datas.add(new ImageNewInfo("3",R.mipmap.huo_02));
        new_datas.add(new ImageNewInfo("4",R.mipmap.huo_03));
        new_datas.add(new ImageNewInfo("5",R.mipmap.huo_04));
        new_datas.add(new ImageNewInfo("6",R.mipmap.huo_05));
        new_datas.add(new ImageNewInfo("7",R.mipmap.huo_06));
        new_datas.add(new ImageNewInfo("8",R.mipmap.huo_07));
        new_datas.add(new ImageNewInfo("9",R.mipmap.huo_08));
        new_datas.add(new ImageNewInfo("11", R.mipmap.huo_09));
        //设置RecyclerView的数据加载
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());  //LinearLayoutManager中定制了可扩展的布局排列接口，子类按照接口中的规范来实现就可以定制出不同排雷方式的布局了
        //配置布局，默认为vertical（垂直布局），下边这句将布局改为水平布局
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        frag_new_rv.setLayoutManager(layoutManager);
        new_adapter = new NewAdapter(getActivity(), new_datas);
        frag_new_rv.setAdapter(new_adapter);

        //热门图片加载
        huo_datas.add(new ImageHuoInfo("1", R.mipmap.new_00));
        huo_datas.add(new ImageHuoInfo("2",R.mipmap.new_01));
        huo_datas.add(new ImageHuoInfo("3",R.mipmap.new_02));
        huo_datas.add(new ImageHuoInfo("4",R.mipmap.new_03));
        huo_datas.add(new ImageHuoInfo("5",R.mipmap.new_04));
        huo_datas.add(new ImageHuoInfo("6",R.mipmap.new_05));
        huo_datas.add(new ImageHuoInfo("7",R.mipmap.new_06));
        huo_datas.add(new ImageHuoInfo("8",R.mipmap.new_07));
        huo_datas.add(new ImageHuoInfo("9",R.mipmap.new_08));
        huo_datas.add(new ImageHuoInfo("11", R.mipmap.new_09));
        huo_datas.add(new ImageHuoInfo("12",R.mipmap.new_10));
        //设置RecyclerView的数据加载
        LinearLayoutManager layoutManager2 = new LinearLayoutManager(getActivity());  //LinearLayoutManager中定制了可扩展的布局排列接口，子类按照接口中的规范来实现就可以定制出不同排雷方式的布局了
        //配置布局，默认为vertical（垂直布局），下边这句将布局改为水平布局
        layoutManager2.setOrientation(LinearLayoutManager.HORIZONTAL);
        frag_huo_rv.setLayoutManager(layoutManager2);
        huo_adapter = new HuoAdapter(getActivity(), huo_datas);
        frag_huo_rv.setAdapter(huo_adapter);

    }

    private void setAdapter() {
        xBanner.setmAdapter(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        xBanner.startAutoPlay();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        new Thread(runnable){}.start();

        //修改状态栏的颜色
        StatusBarCompat.setStatusBarColor(getActivity(), ContextCompat.getColor(getActivity(), R.color.white));
        //修改状态栏字体颜色
        StatusBarUtil.setImmersiveStatusBar(getActivity(), true);
    }

    @Override
    public void loadBanner(XBanner banner, Object model, View view, int position) {
        Glide
                .with(getActivity())
                .load(imgesUrl.get(position))
                .placeholder(R.mipmap.zwtp)
                .error(R.mipmap.zwtp)
                .into((ImageView) view);
    }

    /**
     * 轮播图的
     * @param banner
     * @param position
     */
    @Override
    public void onItemClick(XBanner banner, int position) {
        Intent intent = new Intent(getActivity(), BannerDetailActivity.class);
        startActivity(intent);
    }
}
