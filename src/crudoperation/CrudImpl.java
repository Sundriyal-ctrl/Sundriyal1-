/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crudoperation;

import ConnectionDetails.ConnectionProvider;
import DaoClass.BookDao;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collector;

public class CrudImpl implements crudInterface{

    @Override
    public void insert() {
        try {
            
           BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
            Connection connection=ConnectionProvider.getInstance();
            PreparedStatement pre=connection.prepareStatement("insert into book values(?,?,?,?,?)");
            System.out.println("Enter Id");
            pre.setInt(1,Integer.parseInt(br.readLine()));
            System.out.println("Enter Name");
            pre.setString(2,br.readLine());
            System.out.println("Enter Publisher");
            pre.setString(3,br.readLine());
            System.out.println("Enter Price");
            pre.setInt(4,Integer.parseInt(br.readLine()));
            System.out.println("Enter author");
            pre.setString(5, br.readLine());
            pre.executeUpdate();
            System.out.println("Data Inserted Successfully");
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CrudImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(CrudImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(CrudImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
              
    }

    @Override
    public void delete() {
     BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
            Connection connection=null;
        try {
            connection = ConnectionProvider.getInstance();
       
            PreparedStatement pre = null;
        
            pre = connection.prepareStatement("delete from book where id=?");
     
            System.out.println("Enter Id");
        
            pre.setInt(1,Integer.parseInt(br.readLine()));
            pre.executeUpdate();
            System.out.println("Data Deleted");
               
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void update() {
     try
     {
         BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
      Connection con=ConnectionProvider.getInstance();
      PreparedStatement pre=con.prepareStatement("update book set name=? where id=?");
         System.out.println("Enter Name");
         pre.setString(1,br.readLine());
         System.out.println("Enter Id Which You want to Update");
         pre.setInt(2,Integer.parseInt(br.readLine()));
         pre.executeUpdate();
         System.out.println("Updated Successfully");
     }catch(Exception e)
     {
         System.out.println(e.getMessage());
     }
    }

    @Override
    public void fetch() {
        ArrayList<BookDao> hash=this.getData();
        System.out.println(hash);
    }

    @Override
    public void create() {
        try {
            Connection connection=ConnectionProvider.getInstance();
            PreparedStatement pre=connection.prepareStatement("create table Book(id int primary key,"
                    + "name varchar(233),publisher varchar(233),price int,author varchar(233))");
            if(pre.execute())
            {
                System.out.println("Database Created");
            }
            else
            {
                System.out.println("Already Exists");
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CrudImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(CrudImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
      
  public  ArrayList<BookDao> getData()
  {
       ArrayList<BookDao> Books= new  ArrayList<>();
     try 
     {
         Connection con=ConnectionProvider.getInstance();
         PreparedStatement pre=con.prepareStatement("select *from book");
         
         BookDao book= null;
         ResultSet rt=pre.executeQuery();
         while(rt.next())
         {
             book = new BookDao();
             book.setId(rt.getInt(1));
             book.setName(rt.getString(2));
             book.setPublisher(rt.getString(3));
             book.setPrice(rt.getInt(4));
             book.setAuthor(rt.getString(5));
             Books.add(book);
         }
     }catch(Exception e)
     {
         System.out.println(e.getMessage());
     }
     return Books;
  }
  public void sortById()
  {
      ArrayList<BookDao> hash=this.getData();
      hash.sort(Comparator.comparing(s->s.getId(),Collections.reverseOrder()));
     
      System.out.println(hash);
  }
  public void sortByPublisher()
  {
       ArrayList<BookDao> hash=this.getData();
     hash.sort(Comparator.comparing(s->s.getPublisher()));
      System.out.println(hash);
  }
  
  public void sort()
  {
       try 
     {
         Connection con=ConnectionProvider.getInstance();
         PreparedStatement pre=con.prepareStatement("select A.publisher,B.author from Book A inner join Book B on A.id=B.id order by(A.publisher)");
         ResultSet rt=pre.executeQuery();
         while(rt.next())
         {
             System.out.println(rt.getString(1)+"   "+rt.getString(2));
         }
     }catch(Exception e)   
     {
         System.out.println(e.getMessage());
     }
  }
}
