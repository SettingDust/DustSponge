package com.settingdust.blockregen.module.data;

import com.google.common.reflect.TypeToken;
import ninja.leaping.configurate.ConfigurationNode;
import ninja.leaping.configurate.objectmapping.ObjectMappingException;
import ninja.leaping.configurate.objectmapping.serialize.TypeSerializer;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;

import java.util.Date;

public class DateSerializer implements TypeSerializer<Date> {
    @Nullable
    @Override
    public Date deserialize(@NonNull TypeToken<?> type, @NonNull ConfigurationNode value) throws ObjectMappingException {
        return new Date(value.getLong());
    }

    @Override
    public void serialize(@NonNull TypeToken<?> type, @Nullable Date obj, @NonNull ConfigurationNode value) throws ObjectMappingException {
        value.setValue(obj.getTime());
    }
}
