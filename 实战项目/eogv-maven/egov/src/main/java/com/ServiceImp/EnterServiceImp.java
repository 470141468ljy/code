package com.ServiceImp;

import com.Util.SqlSessionUtil;
import com.dao.EnterDao;
import com.domain.En_Inv;
import com.domain.Enterprise;
import com.domain.Invest;
import com.domain.Page;
import com.service.EnterService;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EnterServiceImp implements EnterService {
    EnterDao enterDao= SqlSessionUtil.getCurrentSqlSession().getMapper(EnterDao.class);
    /**
     * ��ѯ��֯���������Ƿ����
     * @param orgcode
     * @return
     */
    @Override
    public boolean selectOrgcode(String orgcode) {
        Enterprise enterprise=enterDao.selectOrg(orgcode);
        if (enterprise!=null){
            return false;
        }
        return true;
    }

    /**
     * ���ע�ṫ˾��Ϣ��������ӹ�����Ͷ���˵Ĺ�ϵ��
     * @param request
     * @param obj
     * @param list
     */
    @Override
    public boolean SaveEnterandInv(HttpServletRequest request, Object obj, List<En_Inv> list) {
            int count1=enterDao.SaveEnter((Enterprise)obj);
            int count2=enterDao.SaveEnterandInv(list);
        if (count1>0&&count2>0){
            return true;
        }
        return false;
    }


    /**
     * ��ҳ��ѯ��ҵ��Ϣ
     */
    @Override
    public void dynamicSelectEnter(HttpServletRequest request, Map<String, Object> map, int pageSize, int pageNo) {
       List<Object> list=enterDao.querySelectEnter(map);
       int total=list.size();
        int pagecount=total%pageSize==0?total/pageSize:(total/pageSize)+1;
        pageNo=pageNo>pagecount?pagecount:pageNo;
        int begin=(pageNo-1)*pageSize;
        int size=(pageSize*pageNo)>list.size()?(list.size()-(pageNo-1)*pageSize):pageSize;

        //����Page����
        Page page=new Page();
        page.setPagecount(pagecount);
        page.setPageno(pageNo>pagecount?pagecount:pageNo);
        page.setPagesize(pageSize);
        page.setTotalsize(total);

        map.put("begin",begin);
        map.put("size",size);
        //��ȡָ��ҳ����user
        List<Object> list2=enterDao.querySelectEnter(map);
        request.setAttribute("list",list2);
        request.setAttribute("page",page);
    }
}