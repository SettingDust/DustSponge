package com.settingdust.dustdeathpenalty.module.main.entity;

import lombok.Data;
import ninja.leaping.configurate.objectmapping.Setting;
import ninja.leaping.configurate.objectmapping.serialize.ConfigSerializable;

@ConfigSerializable
@Data
public class WorldEntity {
    @Setting
    private ItemEntity item = new ItemEntity();
    @Setting
    private ExpEntity exp = new ExpEntity();

    public WorldEntity() {
    }

    public WorldEntity(ItemEntity item, ExpEntity exp) {
        this.item = item;
        this.exp = exp;
    }
}
