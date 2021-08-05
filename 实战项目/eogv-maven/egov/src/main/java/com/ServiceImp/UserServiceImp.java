package com.ServiceImp;

import com.Util.SqlSessionUtil;
import com.dao.UserDao;
import com.domain.Page;
import com.domain.User;
import com.service.UserService;
import org.apache.ibatis.session.SqlSession;
import org.w3c.dom.ls.LSOutput;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

public class UserServiceImp implements UserService {
  private UserDao userDao=SqlSessionUtil.getCurrentSqlSession().getMapper(UserDao.class);
    /**
     * ʵ�����ϵͳ�û�
     * @return
     */
    @Override
    public int addUser(User user) {
        System.out.println(userDao);
        return userDao.insertUser(user);
    }

    /**
     * �߼���ҳ����ѯ���е��û�
     * @return
     */
    @Override
    public void selectAllUser1(HttpServletRequest request,int pageSize,int pageNo) {
        //��ȡsession
        HttpSession session=request.getSession();
        //��session��ȡ��List����
        List<User> biglist=(List<User>)session.getAttribute("BigList");
        //������صĴ�List����Ϊnu11���������ݿ�ִ�в�ѯ��䷵�ؽ����
        //�����������װ�û�����
        //���û�����洢����List������
        //����List���ϴ洢��session������
        if (biglist==null){
            biglist=userDao.selctAllUser1();
            session.setAttribute("BigList",biglist);
        }
       // ����ҳ��Ӵ�List������ȡ��СList���Ͻ�СList���ϴ洢��request��Χ��
        List<User> smallList=new ArrayList<>();
        int beginIndex=(pageNo-1)*pageSize;
        int endIndex=(pageNo*pageSize);
        if(endIndex>biglist.size()){
            endIndex=biglist.size();
        }
        System.out.println(pageNo+","+beginIndex+","+endIndex);
        for (;beginIndex<endIndex;beginIndex++){
            smallList.add(biglist.get(beginIndex));
        }
        System.out.println(smallList.size());
        request.setAttribute("userList",smallList);
    }

    /**
     * �����ҳ��ʵ�ֲ�ѯ���е��û�
     * @param request
     * @param pageSize
     * @param pageNo
     */
    @Override
    public void selectAllUser2(HttpServletRequest request, int pageSize, int pageNo) {

        //��ȡsql�е��ܼ�¼����
        List<User> list1=userDao.selctAllUser1();
        int total=list1.size();
        int pagecount=total%pageSize==0?total/pageSize:(total/pageSize)+1;
        pageNo=pageNo>pagecount?pagecount:pageNo;
        int begin=(pageNo-1)*pageSize;
        int size=(pageSize*pageNo)>list1.size()?(list1.size()-(pageNo-1)*pageSize):pageSize;

        //����Page����
        Page page=new Page();
        page.setPagecount(pagecount);
        page.setPageno(pageNo>pagecount?pagecount:pageNo);
        page.setPagesize(pageSize);
        page.setTotalsize(total);
        System.out.println(pagecount);

        //��ȡָ��ҳ����user
        List<User> list2=userDao.selctAllUser2(begin,size);
        request.setAttribute("userList",list2);
        //��¼ҳ����Ϣ
        request.setAttribute("page",page);





    }

    /**
     * �����û��Ĵ��룬��ѯ��ص���Ϣ
     * @param request
     * @param usercode
     */
    @Override
    public void SelectOneUser(HttpServletRequest request, String usercode) {
        User user=userDao.selectOneUser(usercode);
        request.setAttribute("user",user);
    }

    /**
     * �����û����룬�޸ĸ��û�����Ϣ
     * @param user
     */
    @Override
    public int UpdateOneUser(Object user) {
        return userDao.updateOneUser1((User)user);

    }

    /**
     * �����û����룬ɾ��һ�����߶���û�����Ϣ
     * @param usercodes
     * @return
     */
    @Override
    public int DeleteUser(String[] usercodes) {
       return userDao.deleteUser(usercodes);
    }

    @Override
    public User LoginCheck(User user) {
       return userDao.selectOneUser2(user);
    }

}
