package com.luowj.shoppingmall.home.adapter;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.IntegerRes;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.luowj.shoppingmall.home.bean.ResultBeanData;
import com.luowj.shoppingmall.utils.Constants;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerClickListener;
import com.youth.banner.loader.ImageLoader;
import com.zhy.magicviewpager.transformer.AlphaPageTransformer;
import com.zhy.magicviewpager.transformer.ScaleInTransformer;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.LogRecord;

import app.properties.custom.imooc.com.shoppingmall.R;
import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by hasee on 2017/2/14.
 */
public class HomeRecycleAdapter extends RecyclerView.Adapter {

    /**
     * 五种类型
     */
    /**
     * 横幅广告
     */
    public static final int BANNER = 0 ;
    /**
     * 频道
     */
    public static final int CHANNEL = 1 ;
    /**
     * 活动
     */
    public static final int ACT = 2 ;
    /**
     * 秒杀
     */
    public static final int SECKILL = 3 ;
    /**
     * 推荐
     */
    public static final int RECOMMEND = 4 ;
    /**
     * 热卖
     */
    public static final int HOT = 5 ;
    /**
     * 当前类型
     */
    public int currentType = BANNER;

    /**
     * 数据对象
     */
    private ResultBeanData.ResultBean resultBean;
    private Context mContext;
    private LayoutInflater mLayoutInflater;
    public HomeRecycleAdapter(Context mContext, ResultBeanData.ResultBean resultBean) {
        this.resultBean = resultBean;
        this.mContext = mContext;
        mLayoutInflater = LayoutInflater.from(mContext);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == BANNER){
             return new BannerViewHolder(mLayoutInflater.inflate(R.layout.banner_viewpager,null),mContext);
        }else if(viewType == CHANNEL){
            return new ChannerViewHolder(mLayoutInflater.inflate(R.layout.channel_item,null),mContext);
        }else if(viewType == ACT){
            return new ActViewHolder(mLayoutInflater.inflate(R.layout.act_item,null),mContext);
        }else if(viewType == SECKILL){
            return new SeckillViewHolder(mLayoutInflater.inflate(R.layout.seckill_item,null),mContext);
        }else if(viewType == RECOMMEND){
            return new RecommendViewHolder(mLayoutInflater.inflate(R.layout.recommend_item,null),mContext);
        }else if(viewType == HOT){
            return new HotViewHolder(mLayoutInflater.inflate(R.layout.hot_item,null),mContext);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) == BANNER){
            BannerViewHolder bannerViewHolder = (BannerViewHolder)holder;
            //设置数据Banner的数据
            bannerViewHolder.setData(resultBean.getBanner_info());
        }else if (getItemViewType(position) == CHANNEL){
            ChannerViewHolder channerViewHolder = (ChannerViewHolder)holder;
            channerViewHolder.setData(resultBean.getChannel_info());
        }else if(getItemViewType(position) == ACT){
            ActViewHolder actViewHolder = (ActViewHolder)holder;
            actViewHolder.setData(resultBean.getAct_info());
        }else if(getItemViewType(position) == SECKILL){
            SeckillViewHolder seckillViewHolder = (SeckillViewHolder)holder;
            seckillViewHolder.setData(resultBean.getSeckill_info());
        }else if(getItemViewType(position) == RECOMMEND){
            RecommendViewHolder recommendViewHolder = (RecommendViewHolder)holder;
            recommendViewHolder.setData(resultBean.getRecommend_info());
        }else if(getItemViewType(position)  == HOT){
            HotViewHolder hotViewHolder = (HotViewHolder)holder;
            hotViewHolder.setData(resultBean.getHot_info());
        }
    }

    @Override
    public int getItemCount() {
        return 6;
    }

    @Override
    public int getItemViewType(int position) {
        switch (position){
            case  BANNER:
                currentType = BANNER;
                break;
            case  CHANNEL:
                currentType = CHANNEL;
                break;
            case  ACT:
                currentType = ACT;
                break;
            case  SECKILL:
                currentType = SECKILL;
                break;
            case  RECOMMEND:
                currentType = RECOMMEND;
                break;
            case  HOT:
                currentType = HOT;
                break;
        }
        return currentType;
    }
    /**
     * 设置适配器
     */
    static class BannerViewHolder extends RecyclerView.ViewHolder{
        @Bind(R.id.banner)
        Banner banner;
        private Context mContext;
        public BannerViewHolder(View itemView,Context context) {
            super(itemView);
            mContext = context;
            ButterKnife.bind(this,itemView);
        }
        public void setData(final List<ResultBeanData.ResultBean.BannerInfoBean> banner_info) {

            List<String> imageUris = new ArrayList<>();

            for (int i = 0 ; i <banner_info.size(); i++){
                imageUris.add(banner_info.get(i).getImage());
            }
            Log.e("TAG","imageUris=="+imageUris.get(1));

            //设置类似手风琴动画
            banner.setBannerAnimation(Transformer.Accordion);
            //设置循环指示点
            banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
            banner.setImages(imageUris)
                    .setImageLoader(new  GlideImageLoader())
                    .start();
            //设置点击事件
            banner.setOnBannerClickListener(new OnBannerClickListener() {
                @Override
                public void OnBannerClick(int position) {
                    Toast.makeText(mContext,"position"+position,Toast.LENGTH_SHORT).show();
                }
            });
        }

        class GlideImageLoader extends ImageLoader {
            @Override
            public void displayImage(Context context, Object path, ImageView imageView) {
                //具体方法内容自己去选择，次方法是为了减少banner过多的依赖第三方包，所以将这个权限开放给使用者去选择
                Glide.with(context)
                        .load(Constants.Base_URL_IMAGE + path)//图片地址
                        .crossFade()
                        .into(imageView);
            }
        }
    }

    static class ChannerViewHolder extends RecyclerView.ViewHolder{
        private Context mContext;
        @Bind(R.id.gv_channel)
        GridView gv_channel;
        public ChannerViewHolder(View itemView ,Context context) {
            super(itemView);
            mContext = context;
            ButterKnife.bind(this,itemView);
        }

        public void setData(List<ResultBeanData.ResultBean.ChannelInfoBean> channel_info) {
            gv_channel.setAdapter(new ChannelAdapter(mContext,channel_info));
            gv_channel.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    if (position <= 8){
                        Toast.makeText(mContext, "position=="+position, Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    static class  ActViewHolder extends RecyclerView.ViewHolder{
        @Bind(R.id.act_viewpager)
        ViewPager act_viewpager;
        private Context mContext;
        public ActViewHolder(View itemView ,Context context) {
            super(itemView);
            mContext = context;
            ButterKnife.bind(this,itemView);
        }

        public void setData(List<ResultBeanData.ResultBean.ActInfoBean> listActInfoBean) {
            //设置每个页面的间距
            act_viewpager.setPageMargin(20);
            //>=3
            act_viewpager.setOffscreenPageLimit(3);
            //设置动画
            act_viewpager.setPageTransformer(true,new AlphaPageTransformer(new ScaleInTransformer()));
            act_viewpager.setAdapter(new ActPagerAdapter(mContext,listActInfoBean));
            act_viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener(){

                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                }

                @Override
                public void onPageSelected(int position) {
                    Toast.makeText(mContext, "position:" + position, Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onPageScrollStateChanged(int state) {

                }
            });
        }
    }
    static class  SeckillViewHolder extends RecyclerView.ViewHolder{
        private Context mContext;
        @Bind(R.id.tv_time_seckill)
        TextView tv_time_seckill;
        @Bind(R.id.tv_more_seckill)
        TextView tv_more_seckill;
        @Bind(R.id.rv_seckill)
        RecyclerView rv_seckill;
        private boolean isFirst = true;
        private int dt = 0;
        private Handler handler =  new Handler(){
            @Override
            public void handleMessage(Message msg) {
                dt = dt - 1000;
                SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
                String time = sdf.format(new Date(dt));
                tv_time_seckill.setText(time);
                handler.removeMessages(0);
                handler.sendEmptyMessageDelayed(0,1000);
                if(dt <= 0 ){
                    //把消息移除
                    handler.removeCallbacksAndMessages(null);
                }
            }
        };
        public SeckillViewHolder(View itemView,Context context) {
            super(itemView);
            mContext = context;
            ButterKnife.bind(this,itemView);
        }

        public void setData(final ResultBeanData.ResultBean.SeckillInfoBean seckillInfoBean) {
            //设置时间
            if(isFirst){
                dt = (int)(Integer.parseInt(seckillInfoBean.getEnd_time()) - System.currentTimeMillis());
                isFirst = false;
            }
            //倒计时开始
            handler.sendEmptyMessageDelayed(0,1000);
            //设置RecyclerView
            rv_seckill.setLayoutManager(new LinearLayoutManager(mContext,LinearLayoutManager.HORIZONTAL,false));
            SeckillRecyclerViewAdapter adapter = new SeckillRecyclerViewAdapter(mContext,seckillInfoBean);
            rv_seckill.setAdapter(adapter);
            adapter.setOnSeckillRecycleView(new SeckillRecyclerViewAdapter.OnSeckillRecycleView() {
                @Override
                public void onItemClick(int position) {
                    Toast.makeText(mContext, "position:" + position,
                            Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
    static class RecommendViewHolder extends RecyclerView.ViewHolder{
        private Context mContext;
        @Bind(R.id.tv_more_recommend)
        TextView tv_more_recommend;
        @Bind(R.id.gv_recommend)
        GridView gv_recommend;

        public RecommendViewHolder(View itemView,Context context) {
            super(itemView);
            mContext = context;
            ButterKnife.bind(this,itemView);
        }

        public void setData(List<ResultBeanData.ResultBean.RecommendInfoBean> data) {
            gv_recommend.setAdapter(new RecommendAdapter(mContext,data));
            gv_recommend.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Toast.makeText(mContext, "position:" + position,
                            Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
    static class HotViewHolder extends RecyclerView.ViewHolder{
        private Context mContext;
        @Bind(R.id.gv_hot)
        GridView gv_hot;

        public HotViewHolder(View itemView,Context context) {
            super(itemView);
            mContext = context;
            ButterKnife.bind(this,itemView);

        }

        public void setData(List<ResultBeanData.ResultBean.HotInfoBean> data) {
            gv_hot.setAdapter(new HotAdapter(mContext,data));
            gv_hot.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                  Toast.makeText(mContext, "position:" + position,
                        Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
 }
