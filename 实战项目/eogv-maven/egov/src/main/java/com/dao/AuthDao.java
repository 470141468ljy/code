package com.dao;

import com.domain.Auth;

public interface AuthDao {
    /**
     * ���׼����Ϣ�������Ϣ
     */
    int saveAuth1(Auth auth);

    /**
     * ���ݺ�׼����Ų�ѯ��׼������ϸ��Ϣ
     */
    Auth SelectAuth(String authno);

    /**
     * �޸ĺ�׼������Ϣ
     */
    int UpdateAuthFeek(String authno);

}
