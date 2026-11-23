<%@ page contentType="text/html;charset=UTF-8" %><%@ page import="com.naturehoney.*,java.util.*" %><%
 Product edit=(Product)request.getAttribute("product"); List<Product> products=(List<Product>)request.getAttribute("products"); String uploaded=(String)session.getAttribute("uploadedImage"); if(uploaded!=null)session.removeAttribute("uploadedImage");
%><!doctype html><html><head><meta charset="utf-8"><meta name="viewport" content="width=device-width,initial-scale=1"><title>Manage products | NatureHoney</title><link rel="stylesheet" href="../assets/style.css"></head><body><header class="top"><a class="brand" href="../index.jsp">🍯 NatureHoney Admin</a><nav><a href="../index.jsp" target="_blank">View store</a><a href="logout">Logout</a></nav></header><main class="wrap admin">
<h1>Product management</h1><p>Add products or update the details shown on your store.</p>
<div class="admin-grid"><section class="panel"><h2><%=edit==null?"Add a product":"Edit product"%></h2>
<form method="post" action="product/save">
<input type="hidden" name="id" value="<%=edit==null?"":edit.id%>">
<label>Product name</label><input name="name" required value="<%=edit==null?"":edit.name%>">
<label>Description</label><textarea name="description" rows="4"><%=edit==null?"":edit.description%></textarea>
<label>Weight / pack size</label><input name="weight" placeholder="500 g" value="<%=edit==null?"":edit.weight%>">
<label>Price (INR)</label><input type="number" name="price" min="0.01" step="0.01" required value="<%=edit==null?"":edit.price%>">
<label>Image filename</label><input id="imageName" name="image" value="<%=uploaded!=null?uploaded:(edit==null?"honey-placeholder.svg":edit.image)%>">
<label class="check"><input type="checkbox" name="active" <%=edit==null||edit.active?"checked":""%>> Visible in customer store</label>
<button class="btn" type="submit">Save product</button> <%if(edit!=null){%><a class="small-btn" href="products">Cancel edit</a><%}%>
</form>
<hr><h3>Upload product image</h3><form method="post" action="upload-image" enctype="multipart/form-data"><input type="file" name="imageFile" accept=".jpg,.jpeg,.png,.webp,.gif" required><button class="small-btn" type="submit">Upload image</button></form><small>Maximum 5 MB. JPG, PNG, WEBP or GIF.</small>
</section><section class="panel"><h2>Current products</h2><%for(Product x:products){%><div class="admin-product"><img src="../images/<%=x.image%>" onerror="this.src='../images/honey-placeholder.svg'"><div><b><%=x.name%></b><p>₹<%=x.price%> · <%=x.weight%> · <%=x.active?"Visible":"Hidden"%></p><a href="products?id=<%=x.id%>">Edit</a> · <a href="product/delete?id=<%=x.id%>" onclick="return confirm('Delete this product?')">Delete</a></div></div><%}%><%if(products.isEmpty()){%><p>No products added yet.</p><%}%></section></div></main></body></html>
