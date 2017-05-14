package com.luowj.shoppingmall.app;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.luowj.shoppingmall.base.BaseFragment;
import com.luowj.shoppingmall.community.fragment.CommunityFragment;
import com.luowj.shoppingmall.home.fragment.HomeFragment;
import com.luowj.shoppingmall.shoppingcart.fragment.ShoppingCartFragment;
import com.luowj.shoppingmall.type.fragment.TypeFragment;
import com.luowj.shoppingmall.user.fragment.UserFragment;

import java.util.ArrayList;

import app.properties.custom.imooc.com.shoppingmall.R;
import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends FragmentActivity {

  /*  @Bind(R.id.frameLayout)
    Fragment fragment;*/
    @Bind(R.id.rg_main)
    RadioGroup rg_main;
    @Bind(R.id.rb_home)
    RadioButton rb_home;
    private ArrayList<BaseFragment> fragments;
    private int position;
    private BaseFragment tempFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //ButterKnife和Activity绑定
        ButterKnife.bind(this);
        //初始化Fragment
        initFragment();
        initListener();
    }

    private void initFragment(){
        fragments = new ArrayList<BaseFragment>();
        fragments.add(new HomeFragment());
        fragments.add(new TypeFragment());
        fragments.add(new CommunityFragment());
        fragments.add(new ShoppingCartFragment());
        fragments.add(new UserFragment());
    }

    private void initListener() {
        rg_main.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rb_home:
                        position = 0;
                        break;
                    case R.id.rb_type:
                        position = 1;
                        break;
                    case R.id.rb_community:
                        position = 2;
                        break;
                    case R.id.rb_cart:
                        position = 3;
                        break;
                    case R.id.rb_user:
                        position = 4;
                        break;
                }

                BaseFragment baseFragment = getFragment(position);
                switchFragment(tempFragment, baseFragment);
            }
        });
        //默认设置首页
        rg_main.check(R.id.rb_home);
    }

    /**
     * 根据位置得到对应的Fragment
     * @param position
     * @return
     */
    private BaseFragment getFragment(int position){
        if (fragments != null && fragments.size() > 0) {
            BaseFragment baseFragment = fragments.get(position);
            return baseFragment;
        }
        return null;
    }

    /**
     * 切换 Fragment
     */
    private void switchFragment(Fragment fromFragment, BaseFragment
            nextFragment) {
        if (tempFragment != nextFragment) {
            tempFragment = nextFragment;
            if (nextFragment != null) {
                FragmentTransaction transaction =
                        getSupportFragmentManager().beginTransaction();
                //判断 nextFragment 是否添加
                if (!nextFragment.isAdded()) {
                    //隐藏当前 Fragment
                    if (fromFragment != null) {
                        transaction.hide(fromFragment);
                    }
                    transaction.add(R.id.frameLayout, nextFragment).commit();
                } else {
                    //隐藏当前 Fragment
                    if (fromFragment != null) {
                        transaction.hide(fromFragment);
                    }
                    transaction.show(nextFragment).commit();
                }
            }
        }
    }
}
