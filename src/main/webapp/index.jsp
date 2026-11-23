<%@ page contentType="text/html;charset=UTF-8" %><%@ page import="com.naturehoney.*,java.util.*" %>
<%
 List<Product> products=new ProductDAO().list(false);
%>
<!doctype html><html><head><meta charset="utf-8"><meta name="viewport" content="width=device-width,initial-scale=1"><title>NatureHoney | Pure Natural Honey</title><link rel="stylesheet" href="assets/style.css"></head><body>
<header class="top"><a class="brand" href="index.jsp">🍯 NatureHoney</a><nav><a href="index.jsp">Home</a><a href="index.jsp#products">Shop Honey</a><a href="mailto:hello@naturehoney.in">Contact</a></nav></header>
<section class="hero"><div><span class="eyebrow">PURE • NATURAL • AUTHENTIC</span><h1>Nature’s sweetness,<br>straight to your home.</h1><p>Discover naturally delicious honey for your everyday moments.</p><a class="btn" href="#products">Explore products</a></div><div class="hero-art">🍯<span>100% Natural<br>Goodness</span></div></section>
<main id="products" class="wrap"><div class="section-head"><div><span class="eyebrow">FROM NATURE TO YOU</span><h2>Our Honey Collection</h2></div><p>Choose your favourite honey</p></div>
<div class="products"><% for(Product x:products){ %><article class="product"><a href="product.jsp?id=<%=x.id%>"><img src="images/<%=x.image%>" alt="<%=x.name%>" onerror="this.src='images/honey-placeholder.svg'"></a><div class="product-info"><h3><%=x.name%></h3><p><%=x.weight%></p><p class="desc"><%=x.description%></p><div class="buy"><strong>₹<%=x.price%></strong><a class="small-btn" href="product.jsp?id=<%=x.id%>">View details</a></div></div></article><% } %></div>
<% if(products.isEmpty()){ %><p class="empty">Our products are being prepared. Please check back soon.</p><% } %></main>
<footer>© NatureHoney · Pure goodness from nature</footer></body></html>
