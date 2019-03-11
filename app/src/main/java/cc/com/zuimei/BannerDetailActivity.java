package cc.com.zuimei;

import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;

import com.cc.zuimei.systembar.StatusBarCompat;
import com.cc.zuimei.systembar.StatusBarUtil;
import com.wang.avi.AVLoadingIndicatorView;

/**
 * 轮播图详情界面
 */
public class BannerDetailActivity extends AppCompatActivity {

    Activity activity = BannerDetailActivity.this;

    AVLoadingIndicatorView aiv;

    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //去掉标题栏
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_banner_detail);

        //修改状态栏的颜色
        StatusBarCompat.setStatusBarColor(activity, ContextCompat.getColor(activity, R.color.white));
        //修改状态栏字体颜色
        StatusBarUtil.setImmersiveStatusBar(activity, true);

        initView();
    }

    /*小时光是一个的记录日记的APP,风格独特，界面简单。

    主要使用到的东西：Viewpager、SQLite、okhttp、AsyncTask、handler、ScrollView、ListView、第三方分享（分享到QQ朋友和QQ空间）、第三方登录（QQ）等。

    主要界面为两个：首页+个人中心
    首页分为标题栏快速搜索功能和LisrView显示功能；
    标题栏上方添加了一个EditText，通过全局的关键字搜索，快速找到指定内容，
    下面用来显示日记(用ListView显示内容,用AsyncTask加载数据)，用户可通过点击lv的item想去展开一个当前日记的简介，点击展开内容打开此条日记的完整信息，并实现分享功能。
    主界面支持上拉刷新下拉加载，长按item删除，界面简洁操作简单。

    用户个人页面：修改密码、日记收藏、安全设置和风格选择等七八个选项
    用户可根据当前密码去重新设置一个新密码，也可以在首页添加自己喜欢的日记收藏，为保证内容的安全，还可通过临时锁的方式打开一个锁定界面用来保护，在夜间还可以使用黑色格调保护视力等。



    路奔宝是一个保障千万车主出行的APP，功能齐全，服务周到，保质保量。

    使用到的东西：Viewpager、AsyncTask、handler、SQLite、okhttp、ScrollView、Banner、RecyclerView、Glide第三方加载图片框架、WebView、第三方微信分享、第三方微信登录和第三方支付宝支付等。

    主要界面为四个：首页+消息+商城+个人中心
    首页包括轮播图+RV（横向排列）+汽车型号查询功能；
    界面最上方为轮播图，用来加载当前app所打折活动和商城配件活动，车主可手动点击查看并分享该活动。下面为横向排列RV显示车辆等级（类似跑车和非跑车之分），点击rv项打开当前级别的所有汽车名称，可以通过点击选中。并在RV下面的查询条中查询当前车辆的所有维修方式供车主选择。

    消息：本页内容为车主所对应的所有消息，显示三个页面切换按钮：进行中、已完成和系统消息，并对应界面显示出来；

    商城：本页通过链接加载网页实现网页版商城配件信息，并支持对任意配件进行购买（支付宝），购买记录可在个人中心界面账单中查看。

    个人中心：本页布局显示为网格状分布，内容实现记录查询、配件收藏、实名认证、客服帮助等大模块；
    车主可以对照订单查询自己的购买记录、也可以查看自己所心仪的配件信息，如果发现有问题可以即时联系在线客服去解决疑惑。




    游戏宝是集公司所有已发行和运营的安卓游戏APP。种类众多，攻略齐全。

    使用到的东西：Viewpager、自定义状态栏、AsyncTask、handler、okhttp、SQLite、ScrollView、Banner、RecyclerView、Glide第三方加载图片框架、WebView和第三方支付

    主界面为五个：首页+活动+游戏圈+礼包+我的
    首页主要加载所有游戏的信息，通过玩家搜索次数显示为热门游戏、必玩游戏、和新游推荐等，并可通过点击游戏头像打开该游戏相关信息并可以下载；

    活动：本页显示所有活动内容，使用轮播图加载热门活动和折扣活动。其他活动通过列表加载填充在页面上，玩家可通过时间或游戏去筛选活动内容去参与活动。

    游戏圈：玩家可以针对游戏发起提问，并根据游戏名称和游戏类别筛选游戏圈问答内容，所有玩家可以针对任意一条内容进行点赞和评论互动。游戏客服人员可针对游戏问题进行解答并推送信息给玩家。

    礼包：本页显示所有游戏礼包信息。玩家可以在手机已下载的游戏礼包中直接领取礼包，也可以领取未下载的游戏礼包去体验新游戏。所有已领取的礼包卡密存于“存号箱”中，玩家可以在存号箱中选择礼包卡密进入对应的游戏去兑换道具内容。

    我的：本页用LV显示列表加载我的钱包、会员中心、存号箱、消息动态、在线客服等大模块。
    玩家可以使用钱包中所剩金币余额去兑换中心兑换游戏道具，通过充值金额去升级账号等级。游戏客服将24小时解决玩家所遇到的游戏问题，让玩家的游戏体验更加完美。*/

    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            imageView.setImageResource(R.mipmap.banner_01);
        }
    };

    private void initView() {
        aiv = findViewById(R.id.loading_frag_tj);
        imageView = findViewById(R.id.img_banner_detail);
        new Thread(){
            @Override
            public void run() {
                try {
                    this.sleep(3000);
                    handler.sendMessage(Message.obtain());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }
}