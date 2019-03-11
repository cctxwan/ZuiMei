package com.cc.zuimei.test.asynctask;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import cc.com.zuimei.R;

/**
 * 异步加载数据并显示在listview上
 * @author cc
 */
public class AsyncTaskActivity extends AppCompatActivity {

    private ListView at_listview;

    /** 请求URL地址(返回数据中只获取img和内容标题) */
    public static final String url = "http://v.juhe.cn/toutiao/index?type=tiyu&key=0bea107901b817f31bafbda687d2753d";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_async_task);

        at_listview = findViewById(R.id.at_listview);

        //启动异步加载任务
        new News_AnsycTask().execute(url);
    }

    /**
     * 请求数据
     */
    class News_AnsycTask extends AsyncTask<String, Void, List<NewInfo>>{

        /**
         * 这个方法里面的参数就是请求结束的的参数
         * 可以直接放入到adapter里面去
         * @param newInfos
         */
        @Override
        protected void onPostExecute(List<NewInfo> newInfos) {
            super.onPostExecute(newInfos);
            //实现适配器，并显示到listview上
            NewAdapter adapter = new NewAdapter(AsyncTaskActivity.this, newInfos, at_listview);
            at_listview.setAdapter(adapter);
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Log.i("aact", "onPreExecute");
            Toast.makeText(AsyncTaskActivity.this, "onPreExecute", Toast.LENGTH_SHORT).show();
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
            Log.i("aact", "onProgressUpdate");
            Toast.makeText(AsyncTaskActivity.this, "onProgressUpdate", Toast.LENGTH_SHORT).show();
        }

        /**
         * 执行请求操作
         * @param strings
         * @return
         */
        @Override
        protected List<NewInfo> doInBackground(String... strings) {
            //url地址只有一个
            return getDatas(strings[0]);
        }
    }

    /**
     * 用来请求
     * @param url
     * @return
     */
    private List<NewInfo> getDatas(String url) {
        //将下载到的数据存入集合
        List<NewInfo> infos = new ArrayList<>();
        try {
            //获取数据并解析赋值给集合
            String result = getNetwork(new URL(url).openStream());
            NewInfo info = new Gson().fromJson(result, NewInfo.class);
            for (int i = 0; i < info.getResult().getData().size(); i ++){
                NewInfo data = new NewInfo();
                data.setResult(info.getResult());
                infos.add(data);
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return infos;
    }


    /**
     * 下载网络数据
     * @param is
     * @return
     */
    private String getNetwork(InputStream is){
        InputStreamReader isr;
        String result = "";
        try {
            String line = "";
            isr = new InputStreamReader(is, "utf-8");
            BufferedReader br = new BufferedReader(isr);
            while ((line = br.readLine()) != null){
                result += line;
            }
            //释放资源
            isr.close();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }

}
