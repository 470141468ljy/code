package com.Util;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;


import java.io.IOException;


public class SqlSessionUtil {
   private static SqlSessionFactory factory=null;
   private static ThreadLocal<SqlSession> local=new ThreadLocal<>();
   static {
       String fileName="Mybatis.xml";
       try {
           factory= new SqlSessionFactoryBuilder().build(Resources.getResourceAsStream(fileName));
       } catch (IOException e) {
           e.printStackTrace();
       }
   }


    /***
     * ��ȡsqlSession
     * @return
     */
   public static SqlSession getCurrentSqlSession() {
       SqlSession sqlSession = local.get();
       if (sqlSession == null) {
           sqlSession = factory.openSession();
           local.set(sqlSession);
       }
       return sqlSession;
   }

    /***
     * �ع�����
     */
    public static void RollBack(SqlSession sql){
        if (sql!=null){
            sql.rollback();
        }
    }
    /**
     * �ر����Ӷ���,���Ӵ��뵱ǰ�߳� ������
     */
    public static void close(SqlSession sql){
        if (sql!=null){
            sql.close();
            local.remove();
        }
    }

}
