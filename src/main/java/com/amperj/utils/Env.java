package com.amperj.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Env {

    private static final Pattern ENV_PATTERN = Pattern.compile("\\$\\{(\\w+)}|\\$(\\w+)");

    private static final Map<String, String> PROPERTIES = new HashMap<>();

    public static String resolve(String input) {
        if (input.isEmpty()) {
            return null;
        }

        if (PROPERTIES.get(input) != null) {
            return PROPERTIES.get(input);
        }

        Matcher matcher = ENV_PATTERN.matcher(input);

        StringBuffer sb = new StringBuffer();
        while(matcher.find()){
            String envVarName = null == matcher.group(1) ? matcher.group(2) : matcher.group(1);
            String envVarValue = System.getenv(envVarName);
            matcher.appendReplacement(sb, null == envVarValue ? "" : envVarValue);
        }
        matcher.appendTail(sb);
        return sb.toString();
    }

    public static Map<String, String> listProperties(String propertiesLocation) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(propertiesLocation));

        String line = bufferedReader.readLine();
        while (line != null) {

            String[] lineData = line.split("=");
            if (lineData.length > 0) {
                PROPERTIES.put(lineData[0].trim(), lineData[1].trim());
            }

            line = bufferedReader.readLine();
        }
        bufferedReader.close();
        return PROPERTIES;
    }

}
