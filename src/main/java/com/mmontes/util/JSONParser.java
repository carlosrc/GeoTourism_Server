package com.mmontes.util;


import com.amazonaws.util.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class JSONParser {

    public static JSONObject parseJSON(InputStream is) throws Exception {
        BufferedReader streamReader = new BufferedReader(new InputStreamReader(is));
        StringBuilder responseStrBuilder = new StringBuilder();

        String inputStr;
        while ((inputStr = streamReader.readLine()) != null)
            responseStrBuilder.append(inputStr);
        return new JSONObject(responseStrBuilder.toString());
    }
}