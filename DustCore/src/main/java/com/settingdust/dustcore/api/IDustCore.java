package com.settingdust.dustcore.api;

import com.settingdust.dustcore.locale.LocaleManager;

public interface IDustCore {
    IDustCore getInstance();

    LocaleManager getLocaleManager();
}
