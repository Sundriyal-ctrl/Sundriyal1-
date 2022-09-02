
package javaapplication10;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class MenuDriven {
  void showMenu()    
  {
      boolean condition=true;
      BufferedReader b= new BufferedReader(new InputStreamReader(System.in));
      while(condition)
      {
          
          System.out.println("Enter 1 For Create Table");
          System.out.println("Enter 2 For Insert Data");
          System.out.println("Enter 3 For Update Data");
          System.out.println("Enter 4 For Delete Data");
          System.out.println("Enter 5 For Fetch Data");
          System.out.println("Enter 6 For Sort By Id");
          System.out.println("Enter 7 For Sort By Publisher");
          System.out.println("Enter 8 For Sort By Publisher And Author");
          System.out.println("Enter Choice");
          try{
          switch(Integer.parseInt(b.readLine()))
          {
              case 1:
                  new crudoperation.CrudImpl().create();
                  break;
              case 2:
                  new crudoperation.CrudImpl().insert();
                  break;
              case 3:
                  new crudoperation.CrudImpl().update();
                  break;
              case 4:
                  new crudoperation.CrudImpl().delete();
                  break;
              case 5:
                  new crudoperation.CrudImpl().fetch();
                  break;
              case 6:
                  new crudoperation.CrudImpl().sortById();
                  break;
              case 7:
                  new crudoperation.CrudImpl().sortByPublisher();
                  break;
              case 8:
                  new crudoperation.CrudImpl().sort();
                  break;
                  
          }
         }catch(Exception e)
         {
             System.out.println(e.getMessage());
                 
         }
      }
  }
}
