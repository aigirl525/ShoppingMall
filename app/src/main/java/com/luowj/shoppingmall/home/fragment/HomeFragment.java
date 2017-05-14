package com.luowj.shoppingmall.home.fragment;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.luowj.shoppingmall.base.BaseFragment;
import com.luowj.shoppingmall.home.adapter.HomeRecycleAdapter;
import com.luowj.shoppingmall.home.bean.ResultBeanData;
import com.luowj.shoppingmall.utils.Constants;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import app.properties.custom.imooc.com.shoppingmall.R;
import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by hasee on 2017/2/12.
 */
public class HomeFragment extends BaseFragment{
    private static final String TAG = HomeFragment.class.getSimpleName();

    @Bind(R.id.rv_home)
    RecyclerView rvHome;
    @Bind(R.id.ib_top)
    ImageView ib_top;
    @Bind(R.id.tv_search_home)
    TextView tv_search_home;
    @Bind(R.id.tv_message_home)
    TextView tv_message_home;
    private HomeRecycleAdapter adapter;
    private ResultBeanData.ResultBean resultBean;
    @Override
    public View initView() {
        Log.e(TAG, "主页视图被初始化了");
        View view = View.inflate(mContext, R.layout.fragment_home,null);
        ButterKnife.bind(this, view);
        //设置点击事件
        initListener();
        return view;
    }

    private void initListener() {
        //置顶的监听
        ib_top.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              //回到顶部
                rvHome.scrollToPosition(0);
            }
        });
        //搜索的监听
        tv_search_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "搜索",
                        Toast.LENGTH_SHORT).show();
            }
        });
        //消息的监听
        tv_message_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "进入消息中心",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void initData() {
        super.initData();
        getDataFromNet();
        Log.e(TAG, "主页数据被初始化了");
    }

    public void getDataFromNet(){
        OkHttpUtils.get()
                .url(Constants.HOME_URL)
                .id(100)
                .build()
                .execute(new MyStringCallback());
    }
    public class MyStringCallback extends StringCallback{
        @Override
        public void onError(okhttp3.Call call, Exception e, int id) {
            Log.e(TAG, "联网失败" + e.getMessage());
        }
        @Override
        public void onResponse(String response, int id) {
            //当联网成功后会回调这里
            switch (id){
                case 100:
                    if (response != null){
                        processData(response);
                        adapter = new HomeRecycleAdapter(mContext,resultBean);
                        rvHome.setAdapter(adapter);
                        GridLayoutManager manager = new GridLayoutManager(getActivity(),1);
                        manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                            @Override
                            public int getSpanSize(int position) {
                                if (position <= 3){
                                    ib_top.setVisibility(View.GONE);
                                }else{
                                    ib_top.setVisibility(View.VISIBLE);
                                }
                                return 1;
                            }
                        });
                        rvHome.setLayoutManager(manager);
                    }
                    break;

                case 101:break;
            }
        }
    }

    private void processData(String json) {
        if (json != null){
            ResultBeanData resultBeanData = JSON.parseObject(json,ResultBeanData.class);
            resultBean = resultBeanData.getResult();
            Log.e(TAG,"resultBean=="+resultBean.getBanner_info().get(0).getImage());
        }
    }

}
