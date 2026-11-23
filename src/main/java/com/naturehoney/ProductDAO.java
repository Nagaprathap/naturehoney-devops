package com.naturehoney;
import java.sql.*; import java.util.*; import java.math.BigDecimal;
public class ProductDAO {
 public List<Product> list(boolean admin) throws SQLException {
  List<Product> out=new ArrayList<>();
  String sql="SELECT * FROM products "+(admin?"ORDER BY id DESC":"WHERE active=1 ORDER BY id DESC");
  try(Connection c=DBConnection.getConnection(); PreparedStatement p=c.prepareStatement(sql); ResultSet r=p.executeQuery()){
   while(r.next()) out.add(map(r));
  } return out;
 }
 public Product get(int id) throws SQLException {
  try(Connection c=DBConnection.getConnection();PreparedStatement p=c.prepareStatement("SELECT * FROM products WHERE id=?")){
   p.setInt(1,id);try(ResultSet r=p.executeQuery()){return r.next()?map(r):null;}
  }
 }
 public void save(Integer id,String name,String description,String weight,BigDecimal price,String image,boolean active)throws SQLException{
  if(id==null){
   try(Connection c=DBConnection.getConnection();PreparedStatement p=c.prepareStatement("INSERT INTO products(name,description,weight,price,image,active) VALUES(?,?,?,?,?,?)")){
    fill(p,name,description,weight,price,image,active);p.executeUpdate();
   }
  } else {
   String sql="UPDATE products SET name=?,description=?,weight=?,price=?,image=?,active=? WHERE id=?";
   try(Connection c=DBConnection.getConnection();PreparedStatement p=c.prepareStatement(sql)){
    fill(p,name,description,weight,price,image,active);p.setInt(7,id);p.executeUpdate();
   }
  }
 }
 private void fill(PreparedStatement p,String n,String d,String w,BigDecimal price,String image,boolean active)throws SQLException{
  p.setString(1,n);p.setString(2,d);p.setString(3,w);p.setBigDecimal(4,price);p.setString(5,image);p.setBoolean(6,active);
 }
 public void delete(int id)throws SQLException{
  try(Connection c=DBConnection.getConnection();PreparedStatement p=c.prepareStatement("DELETE FROM products WHERE id=?")){p.setInt(1,id);p.executeUpdate();}
 }
 private Product map(ResultSet r)throws SQLException{return new Product(r.getInt("id"),r.getString("name"),r.getString("description"),r.getString("weight"),r.getBigDecimal("price"),r.getString("image"),r.getBoolean("active"));}
}
