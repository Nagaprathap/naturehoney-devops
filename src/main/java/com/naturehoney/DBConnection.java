package com.naturehoney;
import java.sql.*;
public class DBConnection {
  private static final String URL = System.getenv().getOrDefault("NH_DB_URL","jdbc:mysql://localhost:3306/naturehoney?useSSL=false&serverTimezone=UTC");
  private static final String USER = System.getenv().getOrDefault("NH_DB_USER","naturehoney");
  private static final String PASS = System.getenv().getOrDefault("NH_DB_PASSWORD","CHANGE_ME");
  static { try { Class.forName("com.mysql.cj.jdbc.Driver"); } catch (ClassNotFoundException e) { throw new ExceptionInInitializerError(e); } }
  public static Connection getConnection() throws SQLException { return DriverManager.getConnection(URL,USER,PASS); }
}
