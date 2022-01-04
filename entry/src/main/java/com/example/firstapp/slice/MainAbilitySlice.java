package com.example.firstapp.slice;

import com.example.firstapp.ResourceTable;
import ohos.aafwk.ability.AbilitySlice;
import ohos.aafwk.content.Intent;
import ohos.agp.components.Component;
import ohos.agp.components.TabList;

public class MainAbilitySlice extends AbilitySlice {
    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        super.setUIContent(ResourceTable.Layout_ability_main);

        TabList tabList = findComponentById(ResourceTable.Id_tab_list);
        String[] tabListTag={"首页","分类","购物车","我的"};
        for(int i=0;i<tabListTag.length;i++){
            TabList.Tab tab = tabList.new Tab(this);
            tab.setText(tabListTag[i]);
            tabList.addTab(tab);

        }

    }

    @Override
    public void onActive() {
        super.onActive();
    }

    @Override
    public void onForeground(Intent intent) {
        super.onForeground(intent);
    }
}
