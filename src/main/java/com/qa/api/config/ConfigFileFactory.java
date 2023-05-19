package com.qa.api.config;

import org.aeonbits.owner.ConfigCache;

public class ConfigFileFactory{
    public static ConfigReader configReader;
    static {
        configReader = ConfigCache.getOrCreate(ConfigReader.class);
    }
}
