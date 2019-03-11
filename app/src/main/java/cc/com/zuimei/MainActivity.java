package cc.com.zuimei;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.cc.zuimei.fragment.FenleiFrag;
import com.cc.zuimei.fragment.ShezhiFrag;
import com.cc.zuimei.fragment.ShoucangFrag;
import com.cc.zuimei.fragment.TuijianFrag;
import com.cc.zuimei.systembar.StatusBarCompat;
import com.cc.zuimei.systembar.StatusBarUtil;

import java.util.HashMap;

/**
 * 最美
 */
public class MainActivity extends FragmentActivity implements View.OnClickListener {

    /** 上下文 */
    Activity activity = MainActivity.this;

    /** 底部导航栏 */
    LinearLayout Lin_one, Lin_two, Lin_three, Lin_four;

    //获取底部导航栏的ImageView
    ImageView img_one_bottom, img_two_bottom, img_three_bottom, img_four_bottom;

    /** 第一个fragment */
    public static final int PAGE_COMMON = 0;
    /** 第二个fragment */
    public static final int PAGE_TRANSLUCENT = 1;
    /** 第三个fragment */
    public static final int PAGE_COORDINATOR = 2;
    /** 第四个fragment */
    public static final int PAGE_COLLAPSING_TOOLBAR = 3;

    /** 管理fragment */
    private HashMap<Integer,Fragment> fragments = new HashMap<>();

    //当前activity的fragment控件
    private int fragmentContentId = R.id.fragment_content;

    /** 设置默认的fragment */
    private int currentTab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //去掉标题栏
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        initView();
        //判断是否第一次进入app，如果是，弹出隐藏的透明activity进行新人礼包放送
        initFrag();
        // 设置默认的Fragment
        defaultFragment();
        //界面下面的title背景设置
        SelectColor(0);
    }

    private void defaultFragment() {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.add(fragmentContentId,fragments.get(PAGE_COMMON));
        currentTab = PAGE_COMMON;
        ft.commit();
    }

    private void initFrag() {
        fragments.put(PAGE_COMMON, new TuijianFrag());
        fragments.put(PAGE_TRANSLUCENT, new FenleiFrag());
        fragments.put(PAGE_COORDINATOR, new ShoucangFrag());
        fragments.put(PAGE_COLLAPSING_TOOLBAR, new ShezhiFrag());
    }

    private void initView() {
        //底部导航栏的父控件
        Lin_one = findViewById(R.id.Lin_one);
        Lin_two = findViewById(R.id.Lin_two);
        Lin_three = findViewById(R.id.Lin_three);
        Lin_four = findViewById(R.id.Lin_four);

        img_one_bottom = findViewById(R.id.img_one_bottom);
        img_two_bottom = findViewById(R.id.img_two_bottom);
        img_three_bottom = findViewById(R.id.img_three_bottom);
        img_four_bottom = findViewById(R.id.img_four_bottom);


        Lin_one.setOnClickListener(this);
        Lin_two.setOnClickListener(this);
        Lin_three.setOnClickListener(this);
        Lin_four.setOnClickListener(this);
    }

    /**
     * 当页面选中时改变当前的导航栏蓝色和图片的状态
     * @param position 当前页面
     */
    public void SelectColor(int position) {
        if(position == 0){
            //给底部到导航栏的image更换图片
            setBackground(img_one_bottom, R.mipmap.tuijian_visable);
            setBackground(img_two_bottom, R.mipmap.fenlei_gone);
            setBackground(img_three_bottom, R.mipmap.shoucang_gone);
            setBackground(img_four_bottom, R.mipmap.shezhi_gone);
        } else if (position == 1){
            //给底部到导航栏的image更换图片
            setBackground(img_one_bottom, R.mipmap.tuijian_gone);
            setBackground(img_two_bottom, R.mipmap.fenlei_visable);
            setBackground(img_three_bottom, R.mipmap.shoucang_gone);
            setBackground(img_four_bottom, R.mipmap.shezhi_gone);
        } else if (position == 2){
            //给底部到导航栏的image更换图片
            setBackground(img_one_bottom, R.mipmap.tuijian_gone);
            setBackground(img_two_bottom, R.mipmap.fenlei_gone);
            setBackground(img_three_bottom, R.mipmap.shoucang_visable);
            setBackground(img_four_bottom, R.mipmap.shezhi_gone);
        } else if (position == 3){
            //给底部到导航栏的image更换图片
            setBackground(img_one_bottom, R.mipmap.tuijian_gone);
            setBackground(img_two_bottom, R.mipmap.fenlei_gone);
            setBackground(img_three_bottom, R.mipmap.shoucang_gone);
            setBackground(img_four_bottom, R.mipmap.shezhi_visable);
        }
    }

    private void setBackground(ImageView img, int id){
        //图片加载框架
        Glide
                .with(activity)
                .load(id)
                .placeholder(R.mipmap.zwtp)
                .error(R.mipmap.zwtp)
                .into(img);
    }

    /**
     * 点击切换下部按钮
     * @param page
     */
    private void changeTab(int page) {
        //默认的currentTab == 当前的页码，不做任何处理
        if (currentTab == page) {
            return;
        }

        //获取fragment的页码
        Fragment fragment = fragments.get(page);
        //fragment事务
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        //如果该Fragment对象被添加到了它的Activity中，那么它返回true，否则返回false。
        //当前activity中添加的不是这个fragment
        if(!fragment.isAdded()){
            //所以将他加进去
            ft.add(fragmentContentId,fragment);
        }
        //隐藏当前currentTab的
        ft.hide(fragments.get(currentTab));
        //显示现在page的
        ft.show(fragments.get(page));
        //设置当前currentTab底部的状态
        SelectColor(currentTab);
        //当前显示的赋值给currentTab
        currentTab = page;
        //设置当前currentTab底部的状态
        SelectColor(currentTab);
        //activity被销毁？  ！否
        if (!this.isFinishing()) {
            //允许状态丢失
            ft.commitAllowingStateLoss();
        }
    }


    /**
     * 所有的控件在这里进行点击（单击）事件
     * @param v
     */
    @Override
    public void onClick(View v) {
        int temdId = v.getId();
        if(temdId == R.id.Lin_one){
            Log.i("a", "----->1");
            changeTab(PAGE_COMMON);
        } else if (temdId == R.id.Lin_two){
            Log.i("a", "----->2");
            changeTab(PAGE_TRANSLUCENT);
        } else if (temdId == R.id.Lin_three){
            Log.i("a", "----->3");
            changeTab(PAGE_COORDINATOR);
        } else if (temdId == R.id.Lin_four){
            Log.i("a", "----->4");
            changeTab(PAGE_COLLAPSING_TOOLBAR);
        }
    }

}
