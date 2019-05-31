package com.settingdust.dustdeathpenalty.module.main.entity;

import com.google.common.collect.Lists;
import lombok.Data;
import ninja.leaping.configurate.objectmapping.Setting;
import ninja.leaping.configurate.objectmapping.serialize.ConfigSerializable;

import java.util.List;

@ConfigSerializable
@Data
public class ItemEntity {
    @Setting(comment = "Whether drop items")
    private boolean enable = true;
    @Setting(comment = "Is equipment will be dropped")
    private boolean equipment = true;
    @Setting(comment = "Drop rate")
    private double chance = 0.1D;
    @Setting(comment = "The type don't drop, use /itemdb to get")
    private List<String> whitelist = Lists.newArrayList();

    public ItemEntity() {
        whitelist.add("minecraft:torch");
    }
}
