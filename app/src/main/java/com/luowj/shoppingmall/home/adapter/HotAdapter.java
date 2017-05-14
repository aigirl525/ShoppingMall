package com.luowj.shoppingmall.home.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
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

public class HotAdapter extends BaseAdapter {
    private Context context;
    private List<ResultBeanData.ResultBean.HotInfoBean> data;
    public HotAdapter(Context context,List<ResultBeanData.ResultBean.HotInfoBean> data) {
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
            convertView = View.inflate(context, R.layout.item_hot_grid_view,null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder)convertView.getTag();
        }
        viewHolder.tv_name.setText(data.get(position).getName());
        viewHolder.tv_price.setText(data.get(position).getCover_price());
        Glide.with(context).load(Constants.Base_URL_IMAGE + data.get(position).getFigure())
                .into(viewHolder.iv_hot);
        return convertView;
    }
    class ViewHolder{
        @Bind(R.id.iv_hot)
        ImageView iv_hot;
        @Bind(R.id.tv_name)
        TextView tv_name;
        @Bind(R.id.tv_price)
        TextView tv_price;
        public ViewHolder(View view) {
            ButterKnife.bind(this,view);
        }
    }
}
