package com.money.more.common.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.Properties;

/**
 * i18n util
 */
public class I18nUtil {
    private static Logger logger = LoggerFactory.getLogger(I18nUtil.class);

    public static String getString(String key){
        String msg="";
        try {
            // build i18n prop
            String i18nFile = MessageFormat.format("i18n/message{0}.properties", "");
            // load prop
            Resource resource = new ClassPathResource(i18nFile);
            EncodedResource encodedResource = new EncodedResource(resource,"UTF-8");
            Properties prop = PropertiesLoaderUtils.loadProperties(encodedResource);
            msg=prop.getProperty(key);
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }
        return msg;
    }
}
