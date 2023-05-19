package com.qa.api.config;

import org.apache.commons.io.FileUtils;
import org.junit.Test;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

public class TestDataReader {

    private Yaml yaml = null;
    private InputStream inputStream = null;
    private Map<String, Object> obj = null;

    public TestDataReader(String fileName){
         yaml = new Yaml();
         inputStream = this.getClass()
                .getClassLoader()
                .getResourceAsStream(fileName);
         obj = yaml.load(inputStream);

    }

    public Object getYamlValue(String pathValue){
            return getValue(pathValue);
    }
    private Object getValue(String objectIdentifierValue){
        String[] lastKey = objectIdentifierValue.split("\\.");
        return parseMap(this.obj, objectIdentifierValue).get(lastKey[lastKey.length -1]);
    }

    private Map<String, Object> parseMap(Map<String, Object> obj, String objectIdentifierValue){
        if(objectIdentifierValue.contains(".")){
            String[] eachKey = objectIdentifierValue.split("\\.");
            obj = parseMap((Map<String, Object>) obj.get(eachKey[0]), objectIdentifierValue.replace(eachKey[0]+".","" ));
        }
        return obj;
    }



}
