package com.servlet.User;

import com.ServiceImp.UserServiceImp;
import com.Util.MyHandler;
import com.Util.SqlSessionUtil;
import com.domain.User;
import com.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/servlet/PageQueryUserLJ"})
public class PageQueryUserLJ extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //��ȡҳ��
        Integer pageno=Integer.parseInt(request.getParameter("pageno") == null ? "1" : request.getParameter("pageno"));
        //��ȡ�������
        UserService userService=(UserService) new MyHandler(new UserServiceImp()).getProxy();
        userService.selectAllUser1(request,3,pageno);
        //ת��
        request.getRequestDispatcher("/system/user.jsp").forward(request,response);



    }
}
