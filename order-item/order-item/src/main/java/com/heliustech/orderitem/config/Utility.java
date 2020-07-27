package com.heliustech.orderitem.config;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public  class Utility {
    private static int seqValue=100;
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

    public synchronized static final String generateOrderId(){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddhhmmss");
        return formatter.format(new Date());
    }
    public static  int getSeqValue(){
        seqValue=seqValue+1;
        return seqValue;
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
