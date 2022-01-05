package com.example.firstapp.slice;

import com.example.firstapp.ResourceTable;
import com.example.firstapp.provider.TabPageSliderProvider;
import ohos.aafwk.ability.AbilitySlice;
import ohos.aafwk.content.Intent;
import ohos.agp.components.*;

import java.util.ArrayList;
import java.util.List;

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


        List<Integer> list=new ArrayList<>();
        list.add(ResourceTable.Layout_ability_main_index);
        list.add(ResourceTable.Layout_ability_main_category);
        list.add(ResourceTable.Layout_ability_main_shopcart);
        list.add(ResourceTable.Layout_ability_main_user_center);
        PageSlider pageSlider = findComponentById(ResourceTable.Id_page_slider);
        pageSlider.setProvider(new TabPageSliderProvider(list,this));

        //正向联动
        tabList.addTabSelectedListener(new TabList.TabSelectedListener() {
            @Override
            public void onSelected(TabList.Tab tab) {
                int position = tab.getPosition();
                pageSlider.setCurrentPage(position);

                if(position==0){
                    initIndex(pageSlider);
                }else if(position==1){
                    initCategory(pageSlider);
                }else if(position==2){
                    initShopcart(pageSlider);
                }else if(position==3){
                    initUserCenter(pageSlider);
                }
            }

            @Override
            public void onUnselected(TabList.Tab tab) {

            }

            @Override
            public void onReselected(TabList.Tab tab) {

            }
        });

        //反向联动
        pageSlider.addPageChangedListener(new PageSlider.PageChangedListener() {
            @Override
            public void onPageSliding(int i, float v, int i1) {

            }

            @Override
            public void onPageSlideStateChanged(int i) {

            }

            @Override
            public void onPageChosen(int i) {
                if(tabList.getSelectedTabIndex()!=i){
                    tabList.selectTabAt(i);
                }
            }
        });

        //开始加载默认第一个
        tabList.selectTabAt(0);

    }

    private void initUserCenter(PageSlider pageSlider) {

    }

    private void initShopcart(PageSlider pageSlider) {
        Button button = pageSlider.findComponentById(ResourceTable.Id_shopcart_add_button);
        button.setClickedListener(component -> {
                Intent intent=new Intent();
                 present(new OrderAddAbilitySlice(),intent) ;


        });
    }

    private void initCategory(PageSlider pageSlider) {

        //分类页面的商品搜索框添加监听
        TextField searchText = findComponentById(ResourceTable.Id_category_search_textfield);
        searchText.setFocusChangedListener(((component, b) -> {
            if(b){
                present(new SearchAilitySlice(),new Intent());
            }


        }));
    }

    private void initIndex(PageSlider pageSlider) {
        //监听搜索框
        TextField searchText = findComponentById(ResourceTable.Id_index_search_input);
        searchText.setFocusChangedListener(((component, b) -> {
            if(b){
                present(new SearchAilitySlice(),new Intent());
            }


        }));


        Component productLayout = findComponentById(ResourceTable.Id_product01);
            productLayout.setClickedListener(component -> {
                Intent intent=new Intent();
                intent.setParam("productId","10001");
                this.present(new DetailAbilitySlice(),intent);
            });

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
