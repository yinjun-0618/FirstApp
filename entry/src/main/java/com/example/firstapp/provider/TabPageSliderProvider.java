package com.example.firstapp.provider;

import ohos.aafwk.ability.AbilitySlice;
import ohos.agp.components.Component;
import ohos.agp.components.ComponentContainer;
import ohos.agp.components.LayoutScatter;
import ohos.agp.components.PageSliderProvider;

import java.util.List;

public class TabPageSliderProvider extends PageSliderProvider {
    private List<Integer> list;
    private AbilitySlice abilitySlice;

    public TabPageSliderProvider(List<Integer> list, AbilitySlice abilitySlice) {
        this.list = list;
        this.abilitySlice = abilitySlice;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object createPageInContainer(ComponentContainer componentContainer, int i) {
        Integer integer = list.get(i);
        Component parse = LayoutScatter.getInstance(abilitySlice).parse(integer, null, false);
        componentContainer.addComponent(parse);


        return parse;
    }

    @Override
    public void destroyPageFromContainer(ComponentContainer componentContainer, int i, Object o) {
    componentContainer.removeComponent((Component) o);
    }

    @Override
    public boolean isPageMatchToObject(Component component, Object o) {
        return false;
    }
}
