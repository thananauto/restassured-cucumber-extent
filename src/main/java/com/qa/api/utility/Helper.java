package com.qa.api.utility;

import io.cucumber.java.Scenario;
import org.apache.commons.io.FileUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.stream.Collectors;

public class Helper {
    public String getSchemaFromJSON(String folderName, String fileName) throws IOException {
        //String fileSeparator = FileSystems.getDefault().getSeparator();
        String fileSeparator = "/";//File.separator;
        String json = folderName +fileSeparator+fileName+".json";
        return FileUtils.readFileToString(new File(getClass().getClassLoader().getResource(json).getFile()));
        // return schema;
    }

    public String getTagName(Scenario scenario){
        return  scenario.getSourceTagNames().stream()
                .collect(Collectors.joining(""))
                .toLowerCase();
    }

    public JSONObject updateJson(JSONObject obj, String operation, String field, String value) throws Exception {
        // We need to know keys of Jsonobject
        JSONObject json = new JSONObject();
        Iterator iterator = obj.keys();
        String key = null;
        while (iterator.hasNext()) {
            key = (String) iterator.next();
            // if object is just string we change value in key
            if ((obj.optJSONArray(key)==null) && (obj.optJSONObject(key)==null)) {
                if (key.equals(field)) {
                    //the operation decided to whether to remove key or update the key value
                    if(operation.equalsIgnoreCase("set")){
                        obj.put(key, value);
                    }else{
                        obj.remove(key);
                    }
                    return obj;
                }
            }

            // if it's jsonobject
            if (obj.optJSONObject(key) != null) {
                updateJson(obj.getJSONObject(key), operation, field, value);
            }

            // if it's jsonarray
            if (obj.optJSONArray(key) != null) {
                JSONArray jArray = obj.getJSONArray(key);
                for (int i=0;i<jArray.length();i++) {
                    updateJson(jArray.getJSONObject(i), operation, field, value);
                }
            }
        }
        return obj;
    }
}
