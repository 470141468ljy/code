package com.dao;

import com.domain.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserDao {

    /**
     * ���ϵͳ�û�
     */
    int insertUser(User user);
    /**
     * ��ȡ���е�User����װ��List���ϵ���
     */
    List<User> selctAllUser1();

    /**
     *����Limit�﷨����ѯ�����������û�
     */
    List<User> selctAllUser2(@Param("begin") int begin, @Param("size") int size);

    /**
     * �����û����룬��ѯ�û��������Ϣ
     */
    User selectOneUser(String usercode);
    /**
     * �����û����룬�޸��û��������Ϣ
     */
    int updateOneUser1(User user);

    /**
     *  �����û����룬ɾ���������߶���û��������Ϣ
     */
    int deleteUser(String[] usercodes);
    /**
     * �����û����룬��ѯ�û��������Ϣ
     */
    User selectOneUser2(User user);

}
