package com.naturehoney;
import java.math.BigDecimal;
public class Product {
 public int id; public String name, description, weight, image; public BigDecimal price; public boolean active;
 public Product(int id,String name,String description,String weight,BigDecimal price,String image,boolean active){
  this.id=id;this.name=name;this.description=description;this.weight=weight;this.price=price;this.image=image;this.active=active;
 }
}
