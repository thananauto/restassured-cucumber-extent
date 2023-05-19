package com.qa.api.config;

import org.junit.Test;

import java.nio.file.FileSystems;

public class TestMe {

    @Test
    public void test(){
        TestDataReader td = new TestDataReader("TestData.yaml");
        System.out.println(td.getYamlValue("weather-2.cityname"));
        System.out.println(td.getYamlValue("weather-1.radius"));
        System.out.println(td.getYamlValue("weather-3"));

        System.out.println(FileSystems.getDefault().getSeparator());
    }
}
