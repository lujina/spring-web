package com.demo.spring_web.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import org.apache.commons.io.IOUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


public final class StringUtils {
    private static Log logger = LogFactory.getLog(StringUtils.class);
  
    public static String getRandomUUIDStr() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

}
