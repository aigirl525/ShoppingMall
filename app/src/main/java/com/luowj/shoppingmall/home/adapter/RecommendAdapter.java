package com.luowj.shoppingmall.home.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
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

public class RecommendAdapter extends BaseAdapter {
    private List<ResultBeanData.ResultBean.RecommendInfoBean> data;
    private Context context;
    public RecommendAdapter(Context context , List<ResultBeanData.ResultBean.RecommendInfoBean> data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null){
            convertView = View.inflate(context,R.layout.item_recommend_grid_view,null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder)convertView.getTag();
        }
        Glide.with(context).load(Constants.Base_URL_IMAGE + data.get(position).getFigure())
                .into(viewHolder.iv_recommend
        );
        viewHolder.tv_name.setText(data.get(position).getName());
        viewHolder.tv_price.setText("￥" + data.get(position).getCover_price());
        return convertView;
    }
    class ViewHolder{
        @Bind(R.id.iv_recommend)
        ImageView iv_recommend;
        @Bind(R.id.tv_name)
        TextView tv_name;
        @Bind(R.id.tv_price)
        TextView tv_price;
        public ViewHolder(View convertView) {
            ButterKnife.bind(this,convertView);
        }


    }
}
