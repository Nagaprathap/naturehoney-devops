package com.naturehoney;
import jakarta.servlet.annotation.WebServlet;import jakarta.servlet.http.*;import jakarta.servlet.*;import java.io.*;
@WebServlet("/admin/login")
public class AdminLoginServlet extends HttpServlet {
 protected void doPost(HttpServletRequest req,HttpServletResponse resp)throws IOException,ServletException{
  String expectedUser=System.getenv().getOrDefault("NH_ADMIN_USER","admin");
  String expectedPass=System.getenv().getOrDefault("NH_ADMIN_PASSWORD","CHANGE_THIS_ADMIN_PASSWORD");
  String user=req.getParameter("username"),pass=req.getParameter("password");
  if(expectedUser.equals(user)&&expectedPass.equals(pass)){req.getSession(true).setAttribute("nhAdmin",true);resp.sendRedirect(req.getContextPath()+"/admin/products");}
  else {req.setAttribute("error","Invalid username or password");req.getRequestDispatcher("/admin/login.jsp").forward(req,resp);}
 }
}
