package com.zerobank.utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigurationReader {
    private static final Properties properties = new Properties();

    static{
        try {
            FileInputStream file = new FileInputStream("Configuration.properties");
            properties.load(file);
            file.close();
        }catch (IOException e){e.printStackTrace();}
    }

    /**
     * returning the property value
     * @param keyWord;
     * @return;
     */
    public static String getProperty(String keyWord){
        return properties.getProperty(keyWord);
    }
}
