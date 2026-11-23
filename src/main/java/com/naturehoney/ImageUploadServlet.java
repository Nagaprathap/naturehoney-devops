package com.naturehoney;
import jakarta.servlet.annotation.*;import jakarta.servlet.http.*;import jakarta.servlet.*;import jakarta.servlet.annotation.MultipartConfig;
import java.io.*;import java.nio.file.*;import java.util.*;
@WebServlet("/admin/upload-image")
@MultipartConfig(fileSizeThreshold=1024*1024,maxFileSize=5*1024*1024,maxRequestSize=6*1024*1024)
public class ImageUploadServlet extends HttpServlet {
 protected void doPost(HttpServletRequest req,HttpServletResponse resp)throws IOException,ServletException{
  if(!Boolean.TRUE.equals(req.getSession(false)==null?null:req.getSession(false).getAttribute("nhAdmin"))){resp.sendError(401);return;}
  Part part=req.getPart("imageFile");if(part==null||part.getSize()==0){resp.sendRedirect(req.getContextPath()+"/admin/products");return;}
  String submitted=Paths.get(part.getSubmittedFileName()).getFileName().toString();
  String ext=submitted.contains(".")?submitted.substring(submitted.lastIndexOf('.')+1).toLowerCase(Locale.ROOT):"";
  if(!Set.of("jpg","jpeg","png","webp","gif").contains(ext)){resp.sendError(400,"Only JPG, PNG, WEBP and GIF images are allowed");return;}
  String file="product-"+UUID.randomUUID()+"."+ext;
  Path dir=Paths.get(getServletContext().getRealPath("/images"));
  Files.createDirectories(dir);try(InputStream in=part.getInputStream()){Files.copy(in,dir.resolve(file));}
  req.getSession().setAttribute("uploadedImage",file);resp.sendRedirect(req.getContextPath()+"/admin/products");
 }
}
