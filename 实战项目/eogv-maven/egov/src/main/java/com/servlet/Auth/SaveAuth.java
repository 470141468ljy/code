package com.servlet.Auth;

import com.ServiceImp.AuthServiceImp;
import com.Util.MyHandler;
import com.Util.UUIDUtil;
import com.domain.Auth;
import com.domain.User;
import com.service.AuthService;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.List;

@WebServlet(urlPatterns = {"/servlet/SaveAuth"})
public class SaveAuth extends HttpServlet {
    private static Class a=Auth.class;
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DiskFileItemFactory diskFileItemFactory=new DiskFileItemFactory();
        //������һ�ν��յ��ļ���С�������ڸ���ֵ�����������ֿ����
        diskFileItemFactory.setSizeThreshold(1024*1024);
        //���ļ����������õ��ļ������������ļ���Ѱ���ڻ���Ŀ¼�У��������ڣ���ֱ�Ӵ洢��Ŀ��Ŀ¼�У�
        String path="E:\\EGOV��Ŀ\\EGOV\\temp\\";//�޸�·�����Լ����棩
        diskFileItemFactory.setRepository(new File(path));

        //���Ķ���
        ServletFileUpload fileUpload=new ServletFileUpload(diskFileItemFactory);
        fileUpload.setSizeMax(1024*1024*1024);//1G

        //����Auth����
        Auth auth=new Auth();
        String fileName=null;
        boolean ok=false;
        try {
           List<FileItem> items= fileUpload.parseRequest(request);
            for (FileItem item:items) {
                if (item.isFormField()){
                    String name=item.getFieldName();
                    String value=item.getString("UTF-8");
                    System.out.println(name+","+value);
                    String MethedName="set"+name.toUpperCase().charAt(0)+name.substring(1);
                    Method method=a.getDeclaredMethod(MethedName,String.class);
                    method.invoke(auth,value);
                }else{
                    fileName=item.getName();
                    auth.setFilename(fileName);
                    String path2="E:\\EGOV��Ŀ\\EGOV\\upload\\"+fileName;
                    item.write(new File(path2));
                }
            }
            HttpSession session=request.getSession(false);
            User user=(User)session.getAttribute("user");
            auth.setUsercode(user.getUsercode());
            auth.setAuthno(UUIDUtil.getUUID());
            System.out.println(auth);
            AuthService authService=(AuthService)new MyHandler(new AuthServiceImp()).getProxy();
            ok=authService.saveAuto(auth);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if (ok){
                response.sendRedirect(request.getContextPath()+"/auth/inputOrg.jsp");
            }else{
                if (fileName!=null||fileName.equals("")){
                    File file=new File("E:\\EGOV��Ŀ\\EGOV\\upload\\"+fileName);
                    file.delete();
                }

            }
        }
    }
}
