package com.luowj.shoppingmall.home.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.luowj.shoppingmall.home.bean.ResultBeanData;
import com.luowj.shoppingmall.utils.Constants;

import java.util.List;

import app.properties.custom.imooc.com.shoppingmall.R;
import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by hasee on 2017/2/18.
 */

public class SeckillRecyclerViewAdapter extends RecyclerView.Adapter {
    private Context mContext;
    private ResultBeanData.ResultBean.SeckillInfoBean mSeckillInfoBean;
    private List<ResultBeanData.ResultBean.SeckillInfoBean.ListBean> listSeckillInfoBean;
    public SeckillRecyclerViewAdapter(Context context ,ResultBeanData.ResultBean.SeckillInfoBean seckillInfoBean) {
        mContext = context;
        mSeckillInfoBean = seckillInfoBean;
        listSeckillInfoBean = seckillInfoBean.getList();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //return new ViewHolder(View.inflate(mContext, R.layout.item_seckill,null));
        return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_seckill,null));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ViewHolder viewHolder = (ViewHolder)holder;
        viewHolder.setData(position);
    }

    @Override
    public int getItemCount() {
        return listSeckillInfoBean.size();
    }
    class ViewHolder extends  RecyclerView.ViewHolder{
        @Bind(R.id.iv_figure)
        ImageView iv_figure;
        @Bind(R.id.tv_cover_price)
        TextView tv_cover_price;
        @Bind(R.id.tv_origin_price)
        TextView tv_origin_price;
        @Bind(R.id.ll_root)
        LinearLayout ll_root;
        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }

        public void setData(final int position) {
            ResultBeanData.ResultBean.SeckillInfoBean.ListBean listBean = listSeckillInfoBean.get(position);
            tv_cover_price.setText("￥"+ listBean.getCover_price());
            tv_origin_price.setText("￥"+ listBean.getOrigin_price());
            Glide.with(mContext).load(Constants.Base_URL_IMAGE + listBean.getFigure()).into(iv_figure);
            ll_root.setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View v) {
                    if (onSeckillRecycleView != null){
                        onSeckillRecycleView.onItemClick(position);
                    }
                }
            });
        }
    }
    /**
     * 监听器
     */
    public interface OnSeckillRecycleView{
        /**
         * 当某条被点击的时候回调
         */
        public void onItemClick(int position);
    }
    private OnSeckillRecycleView onSeckillRecycleView;

    public void setOnSeckillRecycleView(OnSeckillRecycleView onSeckillRecycleView) {
        this.onSeckillRecycleView = onSeckillRecycleView;
    }
}
