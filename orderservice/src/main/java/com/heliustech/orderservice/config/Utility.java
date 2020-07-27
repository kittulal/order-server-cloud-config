package com.heliustech.orderservice.config;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Map;

public final class Utility {
    public static final boolean isEmpty(String str){
        return str == null || str.isEmpty();
    }
    public static final boolean isEmpty(Object obj){
        return obj == null;
    }
    public static final boolean isEmpty(Collection<?> col){
        return col == null || col.isEmpty();
    }
    public static boolean isEmpty(Map<?, ?> map) {
        return map == null || map.isEmpty();
    }


    public static final String getDate(){
        Date date = new Date();
        //Specifying the format of the date using SimpleDateFormat
        //2020-07-25 20:41:40.353437
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //Formatting the date to the specified format
        String dateString = sdf.format(date);
        return dateString;
    }

}
