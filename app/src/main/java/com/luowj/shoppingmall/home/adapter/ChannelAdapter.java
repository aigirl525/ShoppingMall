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
 * Created by hasee on 2017/2/17.
 */

public class ChannelAdapter extends BaseAdapter {
    private Context mContext;
    private List<ResultBeanData.ResultBean.ChannelInfoBean> channel_info;
    public ChannelAdapter(Context context,List<ResultBeanData.ResultBean.ChannelInfoBean> channel_info) {
        this.mContext = context;
        this.channel_info = channel_info;
    }

    @Override
    public int getCount() {
        return channel_info == null ? 0 : channel_info.size();
    }

    @Override
    public Object getItem(int position) {
        return channel_info.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null){
            convertView = View.inflate(mContext, R.layout.item_channel,null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        ResultBeanData.ResultBean.ChannelInfoBean channelInfoBean = channel_info.get(position);
        viewHolder.tvChannel.setText(channelInfoBean.getChannel_name());
        Glide.with(mContext).load(Constants.Base_URL_IMAGE + channelInfoBean.getImage()).into(viewHolder.ivChannel);
        return convertView;
    }
    class ViewHolder{
        @Bind(R.id.iv_channel)
        ImageView ivChannel;
        @Bind(R.id.tv_channel)
        TextView tvChannel;
        ViewHolder(View view){
            ButterKnife.bind(this,view);
        }
    }
}
