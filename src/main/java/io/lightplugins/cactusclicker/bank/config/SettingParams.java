package io.lightplugins.cactusclicker.bank.config;

import io.lightplugins.cactusclicker.bank.LightBank;

public class SettingParams {

    private final LightBank lightBank;

    public SettingParams(LightBank lightBank) {
        this.lightBank = lightBank;
    }

    public String getModuleLanguage() {
        return lightBank.getSettings().getString("module-language");
    }
}
