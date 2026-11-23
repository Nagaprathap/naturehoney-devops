package com.naturehoney;
import jakarta.servlet.annotation.WebServlet;import jakarta.servlet.http.*;import jakarta.servlet.*;import java.io.*;import java.math.BigDecimal;
@WebServlet(urlPatterns={"/admin/products","/admin/product/save","/admin/product/delete"})
public class AdminProductServlet extends HttpServlet {
 private final ProductDAO dao=new ProductDAO();
 private boolean auth(HttpServletRequest q,HttpServletResponse p)throws IOException{if(Boolean.TRUE.equals(q.getSession(false)==null?null:q.getSession(false).getAttribute("nhAdmin"))==false){p.sendRedirect(q.getContextPath()+"/admin/login.jsp");return false;}return true;}
 protected void doGet(HttpServletRequest q,HttpServletResponse p)throws IOException,ServletException{
  if(!auth(q,p))return;
  try{
   if(q.getServletPath().endsWith("/delete")){dao.delete(Integer.parseInt(q.getParameter("id")));p.sendRedirect(q.getContextPath()+"/admin/products");return;}
   String id=q.getParameter("id");if(id!=null)q.setAttribute("product",dao.get(Integer.parseInt(id)));
   q.setAttribute("products",dao.list(true));q.getRequestDispatcher("/admin/dashboard.jsp").forward(q,p);
  }catch(Exception e){throw new ServletException(e);}
 }
 protected void doPost(HttpServletRequest q,HttpServletResponse p)throws IOException,ServletException{
  if(!auth(q,p))return;
  try{
   String id=q.getParameter("id"),name=q.getParameter("name"),desc=q.getParameter("description"),weight=q.getParameter("weight"),price=q.getParameter("price"),image=q.getParameter("image");
   if(name==null||name.isBlank()||price==null)throw new IllegalArgumentException("Name and price are required");
   if(image==null||image.isBlank())image="honey-placeholder.svg";
   dao.save(id==null||id.isBlank()?null:Integer.valueOf(id),name.trim(),desc,weight,new BigDecimal(price),image,q.getParameter("active")!=null);
   p.sendRedirect(q.getContextPath()+"/admin/products");
  }catch(Exception e){throw new ServletException("Could not save product: "+e.getMessage(),e);}
 }
}
