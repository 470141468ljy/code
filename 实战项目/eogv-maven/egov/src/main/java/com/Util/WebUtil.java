package com.Util;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Enumeration;

public class WebUtil {
    /**
     * ���÷�����ƣ�ʵ���Զ������������ֵ��domaim������
     * --ʹ��ǰ�᣺
     *      --�÷������β�ֻ��һ�����Ҷ�ΪString
     *      --domaim�ĳ�Ա��������������ҳ�е�form���е�name����һ��
     *
     * @param request
     * @param obj
     */
    public static void fromQuestToobj(HttpServletRequest request,Object obj){

        //��ȡdomain���ֽ����ļ�
        Class c=obj.getClass();

        //��ȡ���е� �ֶ���
        Enumeration<String> fieldNames=request.getParameterNames();


        while (fieldNames.hasMoreElements()){

                //��ȡ�ֶ���
                String fieldName=fieldNames.nextElement();

                //ƴ��domain����
                String methodName="set"+fieldName.toUpperCase().charAt(0)+fieldName.substring(1);
            try {
                //�õ���Ҫ���õķ���
                Method method=c.getDeclaredMethod(methodName,String.class);

                //���÷�������̬��ֵ��ֵ��domain����
                method.invoke(obj,request.getParameter(fieldName));
            } catch (Exception e) {
                //e.printStackTrace();
            }

        }



    }
}
