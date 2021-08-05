package com.Util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * ���ڹ�����
 */
public class DateUtil {

    /**
     * ���ص�ǰ����
     * @param pattern
     * @return
     */
    public static String nowFormat(String pattern){
        return new SimpleDateFormat(pattern).format(new Date());
    }

    /**
     * ���ؾ���ʱ������
     * @param date
     * @param pattern
     * @return
     */
    public static String nowFormat(String pattern,Date date){
        return new SimpleDateFormat(pattern).format(date);
    }

}
