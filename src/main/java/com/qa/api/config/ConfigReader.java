package com.qa.api.config;

import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({
        "system:properties",
        "system:env",
        "file:${user.dir}/src/test/resources/runner.properties"
})
public interface ConfigReader extends Config {
    @Key("test.base.url")
    public String baseUrl();

    @Key("test.auth.token")
    public String authToken();

    @Key("test.environment")
    public String environment();
}
