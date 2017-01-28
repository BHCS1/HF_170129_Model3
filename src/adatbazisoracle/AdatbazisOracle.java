package adatbazisoracle;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdatbazisOracle {

  public static void main(String[] args) {
    try {
      Class.forName("oracle.jdbc.driver.OracleDriver");
      Connection kapcsolat=DriverManager.getConnection(
        "jdbc:oracle:thin:@localhost:1521:xe",
        "HR", "hr");
      String SQL=
        "SELECT DEPARTMENT_NAME AS depName, "+
           "COUNT(EMPLOYEE_ID) AS empCount\n" +
        "FROM DEPARTMENTS D, EMPLOYEES E\n" +
        "WHERE D.DEPARTMENT_ID=E.DEPARTMENT_ID\n" +
        "GROUP BY DEPARTMENT_NAME\n" +
        "ORDER BY depName";
      ResultSet eredmény=
        kapcsolat.createStatement().executeQuery(SQL);
      System.out.println("Részleg\tLétszám (fő)");
      while(eredmény.next()) {
        System.out.println(eredmény.getString("depName")+
          "\t"+eredmény.getInt("empCount"));
      }
      kapcsolat.close();
    }
    catch(ClassNotFoundException e) {
      e.printStackTrace();
    }
    catch(SQLException e) {
      System.out.println("Hiba: "+e.getMessage());
    }
  }  
}

//SELECT DEPARTMENT_NAME AS depName, COUNT(EMPLOYEE_ID) AS empCount
//FROM DEPARTMENTS D, EMPLOYEES E
//WHERE D.DEPARTMENT_ID=E.DEPARTMENT_ID
//GROUP BY DEPARTMENT_NAME
//ORDER BY depName
//
//
//SELECT DEPARTMENT_NAME, COUNT(EMPLOYEE_ID)
//FROM DEPARTMENTS D, EMPLOYEES E
//WHERE D.DEPARTMENT_ID=E.DEPARTMENT_ID AND D.DEPARTMENT_ID IN (SELECT DISTINCT DEPARTMENT_ID FROM EMPLOYEES)
//GROUP BY DEPARTMENT_NAME
//
//
//SELECT DEPARTMENT_NAME, FIRST_NAME || ' ' || LAST_NAME
//FROM DEPARTMENTS D, EMPLOYEES E
//WHERE D.DEPARTMENT_ID IN (SELECT DISTINCT DEPARTMENT_ID FROM EMPLOYEES)
//
//
//SELECT DEPARTMENT_NAME, MANAGER_ID
//FROM DEPARTMENTS
//WHERE MANAGER_ID IS NOT NULL
//
