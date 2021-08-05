package com.Util;

import org.apache.ibatis.session.SqlSession;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class MyHandler implements InvocationHandler {

    private Object target;
    public MyHandler(Object target){
        this.target=target;
    }


    /**
     * ��ȡ�������
     */
    public Object getProxy(){
        return Proxy.newProxyInstance(target.getClass().getClassLoader(),target.getClass().getInterfaces(),this);
    }
    /**
     * ��̬����ʵ������Ĵ���
     * @param proxy
     * @param method
     * @param args
     * @return
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        SqlSession sqlSession = null;
        Object value=null;
        try {
            //��������
            sqlSession = SqlSessionUtil.getCurrentSqlSession();
            System.out.println(sqlSession);
            value=method.invoke(target, args);
            //�ύ����
            sqlSession.commit();
        } catch (Exception e) {
            //�ع�����
            SqlSessionUtil.RollBack(sqlSession);
            e.printStackTrace();
        } finally {
            SqlSessionUtil.close(sqlSession);
        }

        return value;
    }
    }

