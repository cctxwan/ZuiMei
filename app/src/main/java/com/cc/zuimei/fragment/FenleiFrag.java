package com.cc.zuimei.fragment;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.cc.zuimei.systembar.StatusBarCompat;
import com.cc.zuimei.systembar.StatusBarUtil;
import com.cc.zuimei.utils.GetDate;
import com.scwang.smartrefresh.layout.api.RefreshFooter;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.RefreshState;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnMultiPurposeListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import cc.com.zuimei.R;

/**
 * 分类的fragment
 */
public class FenleiFrag extends Fragment {

    View view;

    //当前所在的fragment的值，默认为0
    public static int DEFAULTFRAGMENT = 0;

    //控件
    static ViewPager viewPager;
    TabLayout frag_two_tabLayout;

    //添加头部item布局信息
    List<String> titles = Arrays.asList("全部", "自然", "植物", "城市", "人文", "体育", "明星", "动物", "汽车", "文艺");


    //每一个头部item所对应一个item
    static List<Frag_Two_Frag_Item> frag_two_frag_items = new ArrayList<>();

    //适配器
    static FragmentPagerAdapter fragmentPagerAdapter;

    TextView fenlei_txt_date;

    Handler handler = new Handler();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_fenlei, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //初始化view
        initView();
        //初始化数据
        initData();
        new Thread(runnable){}.start();

        //修改状态栏的颜色
        StatusBarCompat.setStatusBarColor(getActivity(), ContextCompat.getColor(getActivity(), R.color.white));
        //修改状态栏字体颜色
        StatusBarUtil.setImmersiveStatusBar(getActivity(), true);
    }

    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            //加载刷新框架
            final RefreshLayout frag_two_refreshLayout = view.findViewById(R.id.frag_two_refreshLayout);


            //越界回弹
            frag_two_refreshLayout.setEnableOverScrollBounce(false);

            //在刷新或者加载的时候不允许操作视图
            frag_two_refreshLayout.setDisableContentWhenRefresh(true);
            frag_two_refreshLayout.setDisableContentWhenLoading(true);

            //监听列表在滚动到底部时触发加载事件（默认true）
            frag_two_refreshLayout.setEnableAutoLoadmore(false);

            frag_two_refreshLayout.setEnableRefresh(false);

            //设置自定义Footer
            frag_two_refreshLayout.setFooterHeight(50);
            frag_two_refreshLayout.setRefreshFooter(new ClassicsFooter(getActivity()));


            /**
             * 正在上拉加载数据中
             */
            frag_two_refreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
                @Override
                public void onLoadmore(RefreshLayout refreshlayout) {
                    Log.i("activity", "444");
                    //设置加载事件为2s
                    frag_two_refreshLayout.finishLoadmore(2000);
                }
            });

            /**
             * sf的事件监听
             */
            frag_two_refreshLayout.setOnMultiPurposeListener(new OnMultiPurposeListener() {
                @Override
                public void onHeaderPulling(RefreshHeader header, float percent, int offset, int headerHeight, int extendHeight) {
                }

                @Override
                public void onHeaderReleasing(RefreshHeader header, float percent, int offset, int headerHeight, int extendHeight) {
                }

                @Override
                public void onHeaderStartAnimator(RefreshHeader header, int headerHeight, int extendHeight) {
                }

                @Override
                public void onHeaderFinish(RefreshHeader header, boolean success) {
                }

                @Override
                public void onFooterPulling(RefreshFooter footer, float percent, int offset, int footerHeight, int extendHeight) {
                }

                @Override
                public void onFooterReleasing(RefreshFooter footer, float percent, int offset, int footerHeight, int extendHeight) {
                }

                @Override
                public void onFooterStartAnimator(RefreshFooter footer, int footerHeight, int extendHeight) {
                }

                @Override
                public void onFooterFinish(RefreshFooter footer, boolean success) {
                }

                @Override
                public void onLoadmore(RefreshLayout refreshlayout) {
                }

                @Override
                public void onRefresh(RefreshLayout refreshlayout) {
                }

                @Override
                public void onStateChanged(RefreshLayout refreshLayout, RefreshState oldState, RefreshState newState) {
                    Log.i("activity", "" + oldState + "-------" + newState);
                    if(newState == RefreshState.RefreshFinish){
                        Log.i("getActivity()", "刷新完成");
                    }
                    if(oldState == RefreshState.RefreshFinish){
                    }
                }
            });


        }
    };

    /**
     * 初始化数据
     */
    private void initData() {

        //循环加载titles数组内容到fragment（每一个tablayout的显示界面都是一个frag）
        for(String title : titles){
            //通过frag对象加载title，并完成实例化
            Frag_Two_Frag_Item instance = Frag_Two_Frag_Item.getInstance(title);
            frag_two_frag_items.add(instance);
        }

        //用适配器完成每一个fragment的加载完成
        fragmentPagerAdapter = new FragmentPagerAdapter(getChildFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                //返回指定的fragment
                return frag_two_frag_items.get(position);
            }

            @Override
            public int getCount() {
                //fragment的个数
                return frag_two_frag_items.size();
            }

            @Override
            public CharSequence getPageTitle(int position) {
                //将titles的内容加载进每一个（position为指定的frag）
                return titles.get(position);
            }
        };

        //通过适配器加载
        viewPager.setAdapter(fragmentPagerAdapter);
        //设置viewpager的缓存个数，全部页面都缓存
        viewPager.setOffscreenPageLimit(7);


        //必须与viewpager绑定，否则效果就看不出来
        frag_two_tabLayout.setupWithViewPager(viewPager);
        //设置tablayout的属性
        frag_two_tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        frag_two_tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

//        for(int i = 0; i < titles.size(); i++){
//            frag_two_tabLayout.addTab(frag_two_tabLayout.newTab());
//        }

        for(int i = 0; i < titles.size(); i++) {
            frag_two_tabLayout.getTabAt(i).setCustomView(makeTabView(i));
        }

        handler.post(getdate);
    }

    Runnable getdate = new Runnable() {
        @Override
        public void run() {
            fenlei_txt_date.setText(GetDate.StringData());
            handler.postDelayed(getdate, 1000);
        }
    };

    /**
     * 引入布局设置图标和标题
     * @param position
     * @return
     */
    private View makeTabView(int position){
        View tabView = LayoutInflater.from(getActivity()).inflate(R.layout.tab_item, null);
        TextView textView = tabView.findViewById(R.id.tab_text);
        ImageView imageView = tabView.findViewById(R.id.tab_img);
        textView.setText(titles.get(position));
        imageView.setImageResource(R.mipmap.xhdq);
        return tabView;
    }

    /**
     * 初始化view
     */
    private void initView() {
        fenlei_txt_date = view.findViewById(R.id.fenlei_txt_date);

        //viewpager
        viewPager = view.findViewById(R.id.frag_two_viewpager);
        //tablayout
        frag_two_tabLayout = view.findViewById(R.id.frag_two_tabLayout);


        /**
         * 设置viewpager的滑动监听事件（左右滑动）
         */
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//                A.C_Log(getActivity(), "------->position=" + position);
            }

            @Override
            public void onPageSelected(int position) {
                Log.i("getActivity()", "onPageSelected----> + position=" + position);
                DEFAULTFRAGMENT = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {
//                A.C_Log(getActivity(), "onPageScrollStateChanged");
            }
        });
    }

}
