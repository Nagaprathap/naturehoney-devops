package com.naturehoney;
import jakarta.servlet.annotation.WebServlet;import jakarta.servlet.http.*;import java.io.*;
@WebServlet("/admin/logout")
public class AdminLogoutServlet extends HttpServlet {
 protected void doGet(HttpServletRequest req,HttpServletResponse resp)throws IOException{HttpSession s=req.getSession(false);if(s!=null)s.invalidate();resp.sendRedirect(req.getContextPath()+"/admin/login.jsp");}
}
