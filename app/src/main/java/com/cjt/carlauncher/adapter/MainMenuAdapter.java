package com.cjt.carlauncher.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.cjt.carlauncher.R;
import com.cjt.carlauncher.bean.MenuBean;

import java.util.ArrayList;
import java.util.List;

/**
 * @PackageName com.cjt.carlauncher.adapter
 * @ProjectName CarLauncher
 * @Author CaoJiangtao
 * @Date on 2017/11/14 11:39
 * @Email cjt2014@qq.com
 * @Describe 首页的菜单的适配器
 * @Version V-1.0.0
 */

public class MainMenuAdapter extends RecyclerView.Adapter<MainMenuAdapter.MyItemHolder> {

    List<MenuBean> menuBeanList = new ArrayList<>();

    private MenuBean getItem(int position){
        return menuBeanList.get(position);
    }

    @Override
    public MyItemHolder onCreateViewHolder(ViewGroup viewGroup, int type) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        return new MyItemHolder(layoutInflater.inflate(R.layout.layout_main_menu_item , viewGroup , false));
    }

    @Override
    public void onBindViewHolder(MyItemHolder myItemHolder, int position) {
        myItemHolder.icon.setImageResource(getItem(position).iconResId);
        myItemHolder.name.setText(getItem(position).name);
    }

    @Override
    public int getItemCount() {
        return menuBeanList.size();
    }

    class MyItemHolder extends RecyclerView.ViewHolder{

        TextView name ;
        ImageView icon ;

        public MyItemHolder(View itemView) {
            super(itemView);
            this.name = (TextView) itemView.findViewById(R.id.main_btn_name);
            this.icon = (ImageView) itemView.findViewById(R.id.main_btn_icon);
        }
    }

    public void refreshData(List<MenuBean> menuBeanList){
        this.menuBeanList = menuBeanList ;
        this.notifyDataSetChanged();
    }

}
