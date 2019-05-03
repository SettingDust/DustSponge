package com.settingdust.forcelocale.module.main;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import lombok.Getter;
import ninja.leaping.configurate.objectmapping.Setting;
import ninja.leaping.configurate.objectmapping.serialize.ConfigSerializable;

import java.util.List;
import java.util.Map;

@Getter
@ConfigSerializable
public class MainEntity {
    @Setting
    private Map<String, List<String>> messages = Maps.newHashMap();
    @Setting
    private Map<String, GuiEntity> gui = Maps.newHashMap();

    public MainEntity() {
        List<String> list = Lists.newArrayList();
        list.add("测试");
        messages.put("test", list);
    }

    @Getter
    @ConfigSerializable
    public static final class GuiEntity {
        //        @Setting
//        private String name;
        @Setting
        private List<ItemEntity> items = Lists.newArrayList();

        @Getter
        @ConfigSerializable
        public static final class ItemEntity {
            @Setting
            private String name;

            @Setting
            private List<String> lore = Lists.newArrayList();

        }
    }
}
