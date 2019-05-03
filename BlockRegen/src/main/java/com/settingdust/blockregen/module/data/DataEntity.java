package com.settingdust.blockregen.module.data;

import com.google.common.collect.Maps;
import lombok.Getter;
import ninja.leaping.configurate.objectmapping.Setting;
import ninja.leaping.configurate.objectmapping.serialize.ConfigSerializable;
import org.spongepowered.api.world.Location;
import org.spongepowered.api.world.World;

import java.util.Date;
import java.util.Map;

@Getter
@ConfigSerializable
public class DataEntity {
    @Setting
    private Map<Location<World>, Date> data = Maps.newHashMap();
}
