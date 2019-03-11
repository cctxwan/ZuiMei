package com.cc.zuimei.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cc.zuimei.adapter.ShouCangAdapter;
import com.cc.zuimei.info.ShouCangInfo;
import com.cc.zuimei.systembar.StatusBarCompat;
import com.cc.zuimei.systembar.StatusBarUtil;
import com.cc.zuimei.utils.GetDate;

import java.util.ArrayList;
import java.util.List;

import cc.com.zuimei.R;

/**
 * 收藏的fragment
 */
public class ShoucangFrag extends Fragment {

    View view;

    RecyclerView frag_three_rv;

    List<ShouCangInfo> datas = new ArrayList<>();

    ShouCangAdapter adapter;

    TextView shoucang_txt_date;

    Handler handler = new Handler();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_shoucang, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initView();
//        new Thread(runnable){}.start();
        initData();

        //修改状态栏的颜色
        StatusBarCompat.setStatusBarColor(getActivity(), ContextCompat.getColor(getActivity(), R.color.white));
        //修改状态栏字体颜色
        StatusBarUtil.setImmersiveStatusBar(getActivity(), true);
    }
    
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            initData();
        }
    };

    private void initData() {
        datas.add(new ShouCangInfo("1", R.mipmap.shoucang_00));
        datas.add(new ShouCangInfo("2", R.mipmap.shoucang_01));
        datas.add(new ShouCangInfo("3", R.mipmap.shoucang_02));
        datas.add(new ShouCangInfo("4", R.mipmap.shoucang_03));
        datas.add(new ShouCangInfo("5", R.mipmap.shoucang_04));
        datas.add(new ShouCangInfo("6", R.mipmap.shoucang_05));
        datas.add(new ShouCangInfo("7", R.mipmap.shoucang_06));
        datas.add(new ShouCangInfo("8", R.mipmap.shoucang_07));
        datas.add(new ShouCangInfo("9", R.mipmap.shoucang_08));
        datas.add(new ShouCangInfo("10", R.mipmap.shoucang_09));
        datas.add(new ShouCangInfo("11", R.mipmap.shoucang_10));
        datas.add(new ShouCangInfo("12", R.mipmap.shoucang_11));


        adapter = new ShouCangAdapter(getActivity(), datas);
        frag_three_rv.setLayoutManager(new LinearLayoutManager(getActivity()));
        frag_three_rv.setAdapter(adapter);

        handler.post(getdate);
    }

    Runnable getdate = new Runnable() {
        @Override
        public void run() {
            shoucang_txt_date.setText(GetDate.StringData());
            handler.postDelayed(getdate, 1000);
        }
    };

    private void initView() {
        shoucang_txt_date = view.findViewById(R.id.shoucang_txt_date);

        frag_three_rv = view.findViewById(R.id.frag_three_rv);
    }

}
