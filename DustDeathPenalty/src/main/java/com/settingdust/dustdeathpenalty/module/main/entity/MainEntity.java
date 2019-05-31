package com.settingdust.dustdeathpenalty.module.main.entity;

import com.google.common.collect.Maps;
import lombok.Data;
import ninja.leaping.configurate.objectmapping.Setting;
import ninja.leaping.configurate.objectmapping.serialize.ConfigSerializable;

import java.util.Map;

@ConfigSerializable
@Data
public class MainEntity {
    @Setting
    private ItemEntity item = new ItemEntity();
    @Setting
    private ExpEntity exp = new ExpEntity();
    @Setting
    private EconomyEntity economy = new EconomyEntity();
    @Setting
    private boolean isSendMsg = false;
    @Setting(comment = "The key is world's name")
    private Map<String, WorldEntity> world = Maps.newHashMap();

    public MainEntity() {
        world.put("world", new WorldEntity());
    }
}
