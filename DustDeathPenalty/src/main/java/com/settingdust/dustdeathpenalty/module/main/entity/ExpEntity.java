package com.settingdust.dustdeathpenalty.module.main.entity;

import lombok.Data;
import ninja.leaping.configurate.objectmapping.Setting;
import ninja.leaping.configurate.objectmapping.serialize.ConfigSerializable;

@ConfigSerializable
@Data
public class ExpEntity {
    @Setting(comment = "Whether drop exp")
    private boolean enable = true;
    @Setting(comment = "Drop rate, lose half of it")
    private double rate = 0.1D;
}
