package com.luowj.shoppingmall.home.adapter;

import android.content.Context;
import android.hardware.input.InputManager;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.luowj.shoppingmall.home.bean.ResultBeanData;
import com.luowj.shoppingmall.utils.Constants;

import java.util.List;

/**
 * Created by hasee on 2017/2/17.
 */

public class ActPagerAdapter extends PagerAdapter {
    private Context mContext;
    private List<ResultBeanData.ResultBean.ActInfoBean> mListActInfoBean;
    public ActPagerAdapter(Context context,List<ResultBeanData.ResultBean.ActInfoBean> listActInfoBean) {
        mContext = context;
        mListActInfoBean = listActInfoBean;
    }

    @Override
    public int getCount() {
        return mListActInfoBean.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ImageView view = new ImageView(mContext);
        view.setScaleType(ImageView.ScaleType.FIT_XY);
        //绑定数据
        Glide.with(mContext).load(Constants.Base_URL_IMAGE+mListActInfoBean.get(position).getIcon_url()).into(view);
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View)object);
    }
}
