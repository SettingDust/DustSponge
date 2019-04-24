package com.settingdust.dustdeathpenalty.module.main.entity;

import lombok.Data;
import ninja.leaping.configurate.objectmapping.Setting;
import ninja.leaping.configurate.objectmapping.serialize.ConfigSerializable;

@ConfigSerializable
@Data
public class ItemEntity {
    @Setting(comment = "Whether drop items")
    private boolean enable = true;
    @Setting(comment = "Is equipment will be dropped")
    private boolean equipment = true;
    @Setting(comment = "Drop chance")
    private double chance = 0.1D;
    @Setting(comment = "The type don't drop")
    private String[] noDrops = {};
}
