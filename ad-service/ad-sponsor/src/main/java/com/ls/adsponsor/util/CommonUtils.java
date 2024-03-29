package com.ls.adsponsor.util;

import com.ls.adcommon.exception.AdException;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.time.DateUtils;

import java.util.Date;

/**
 * @author lijiayin
 */
public class CommonUtils {
    
    private static String[] parsePatterns = {
      "yyyy-MM-dd","yyyy/MM/dd","yyyy.MM.dd"      
    };
    
    public static String md5(String value){
        return DigestUtils.md5Hex(value).toUpperCase();
    }
    
    public static Date parseStringDate(String dateString) throws AdException {
        try {
            return DateUtils.parseDate(dateString, parsePatterns);
        }catch (Exception e){
            throw new AdException(e.getMessage());
        }
    }
}
