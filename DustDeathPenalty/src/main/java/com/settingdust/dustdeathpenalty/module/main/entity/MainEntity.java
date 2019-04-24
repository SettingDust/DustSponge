package com.settingdust.dustdeathpenalty.module.main.entity;

import lombok.Data;
import ninja.leaping.configurate.objectmapping.Setting;
import ninja.leaping.configurate.objectmapping.serialize.ConfigSerializable;

@ConfigSerializable
@Data
public class MainEntity {
    @Setting
    private ItemEntity item = new ItemEntity();
    @Setting
    private ExpEntity exp = new ExpEntity();
}
