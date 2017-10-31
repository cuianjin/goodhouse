package com.web.webstart.base.util;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Created by Jason on 25/11/2016.
 */
@Component
public class DateConvert implements Converter<String, Date>
{
    @Override
    public Date convert(String source) {
        //System.out.println("source:" + source);
        if(XaUtil.isNotEmpty(source)){
            return XaUtil.formatDateString2Date(source, "yyyy-MM-dd hh:mm:ss");
        }
        return null;
    }

}
