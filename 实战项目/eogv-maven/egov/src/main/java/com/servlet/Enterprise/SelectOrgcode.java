package com.servlet.Enterprise;

import com.ServiceImp.EnterServiceImp;
import com.Util.MyHandler;
import com.service.EnterService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@WebServlet(urlPatterns = {"/servlet/SelectOrgcode"})
public class SelectOrgcode extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //��ѯ��֯���������Ƿ���ڣ�����������Խ�����һ�����������򷵻ظ�ҳ��
        String orgcode=request.getParameter("orgcode");
        EnterService enterService=(EnterService)new MyHandler(new EnterServiceImp()).getProxy();
        boolean ok=enterService.selectOrgcode(orgcode);
        if (ok){
            request.getRequestDispatcher("/foreignExchange/inputOrgInfo.jsp").forward(request,response);
        }else{
            request.setAttribute("Error","��֯���������Ѿ����ڣ����������룡");
            request.getRequestDispatcher("/foreignExchange/newInputOrg.jsp").forward(request,response);
        }
    }
}
