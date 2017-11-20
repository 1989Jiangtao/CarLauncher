package com.cjt.carlauncher;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cjt.carlauncher.adapter.MainMenuAdapter;
import com.cjt.carlauncher.adapter.ViewHolder;
import com.cjt.carlauncher.bean.MenuBean;
import com.cjt.carlauncher.map.MapActivity;
import com.cjt.carlauncher.music.MusicActivity;
import com.cjt.carlauncher.util.AppUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import es.dmoral.toasty.Toasty;

public class MainActivity extends BaseActivity {

    @BindView(R.id.parentPanel)
    RelativeLayout parentPanel;
    @BindView(R.id.menu_layout)
    LinearLayout menuLayout;
//    @BindView(R.id.menu_recycle_view)
//    RecyclerView menuRecycleView;

    List<MenuBean> menuBeanList = new ArrayList<>();

    private static final int MENU_INDEX = 0xF4000 ;

//    private MainMenuAdapter menuAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        initData();

        initMenu();

//        menuAdapter = new MainMenuAdapter();
//        // 设置布局方式为横向布局 ， 输出不反转序列
//        menuRecycleView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
//        menuRecycleView.setAdapter(menuAdapter);
//        menuAdapter.refreshData(menuBeanList);

    }

    private void initMenu() {
        for (MenuBean menuBean : menuBeanList) {
            View child = LayoutInflater.from(this).inflate(R.layout.layout_main_menu_item, null);

            TextView name = ViewHolder.get(child, R.id.main_btn_name);
            ImageView icon = ViewHolder.get(child, R.id.main_btn_icon);

            name.setText(menuBean.name);
            icon.setImageResource(menuBean.iconResId);

            child.setId(menuBean.index);
            child.setOnClickListener(new menuItemClick()); // 为图标设置点击事件

            menuLayout.addView(child, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT
                    , LinearLayout.LayoutParams.WRAP_CONTENT, 1.0f));
        }
    }

    private void initData() {
        for (int i = 0; i < getResources().getStringArray(R.array.menus).length; i++) {
            String resName = getResources().getStringArray(R.array.menus)[i];

            MenuBean bean = new MenuBean();
            bean.index = MENU_INDEX + i ; // 给每一个按钮绑定一个自定义的ID
            bean.tag = resName ;
            bean.name = getResources().getString(getResId("string", resName));
            bean.iconResId = getResId("mipmap", resName);
            menuBeanList.add(i, bean);
        }
    }

    private int getResId(String typeName, String arrayName) {
        return getResources().getIdentifier(arrayName, typeName, this.getPackageName());
    }

    class menuItemClick implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case MENU_INDEX+0: // 设置
                    AppUtil.openAppByPackageName(MainActivity.this, "com.android.settings");
                    break;
                case MENU_INDEX+1: // 蓝牙
                    Toasty.info(MainActivity.this , "正在开发，敬请期待！").show();
                    break;
                case MENU_INDEX+2: // 音乐
                    startActivity(new Intent(MainActivity.this , MusicActivity.class));
                    break;
                case MENU_INDEX+3: // 日历
                    Toasty.info(MainActivity.this , "正在开发，敬请期待！").show();
                    break;
                case MENU_INDEX+4: // 收音机
                    Toasty.info(MainActivity.this , "正在开发，敬请期待！").show();
                    break;
                case MENU_INDEX+5: // 导航
                    startActivity(new Intent(MainActivity.this , MapActivity.class));
                    break;
            }
        }
    }

}
