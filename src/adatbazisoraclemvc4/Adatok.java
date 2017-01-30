package adatbazisoraclemvc4;

public interface Adatok {

  String DRIVER = "oracle.jdbc.driver.OracleDriver";
  String URL = "jdbc:oracle:thin:@localhost:1521:xe";
  String USERNAME = "HR";
  String PASSWORD = "hr";
  String SQLRÉSZLEGEK=
    "SELECT DISTINCT DEPARTMENT_NAME AS depName\n" +
    "FROM DEPARTMENTS D, EMPLOYEES E\n" +
    "WHERE D.DEPARTMENT_ID=E.DEPARTMENT_ID\n" +
    "ORDER BY depName";
  String SQLDOLGOZÓADOTTRÉSZLEGBŐL=
    "SELECT FIRST_NAME || ' ' || LAST_NAME AS empName\n" +
    "FROM DEPARTMENTS D, EMPLOYEES E\n" +
    "WHERE E.DEPARTMENT_ID=D.DEPARTMENT_ID AND\n" +
    "  D.DEPARTMENT_NAME=?\n" +
    "ORDER BY empName";
  String SQLRÉSZLEGDOLGOZÓ=
    "SELECT DEPARTMENT_NAME AS depName, FIRST_NAME || ' ' || LAST_NAME AS empName\n" +
    "FROM DEPARTMENTS D, EMPLOYEES E\n" +
    "WHERE E.DEPARTMENT_ID=D.DEPARTMENT_ID \n" +
    "ORDER BY depName, empName";
  
  String SQLRESZLEGEKDOLGOZOKLAJOS=
          "SELECT DISTINCT "
          + "(CASE WHEN E.DEPARTMENT_ID IS NULL THEN ' Without department' ELSE DEPARTMENT_NAME END) depName, "
          + "FIRST_NAME || ' ' || LAST_NAME AS empName "
          + "FROM DEPARTMENTS D, EMPLOYEES E "
          + "WHERE D.DEPARTMENT_ID=E.DEPARTMENT_ID OR E.DEPARTMENT_ID IS NULL "
          + "ORDER BY depName, empName";
  
  String SQLRESZLEGEKDOLGOZOK=
          "SELECT DISTINCT "
          + "(CASE WHEN E.DEPARTMENT_ID IS NULL THEN NULL ELSE DEPARTMENT_NAME END) depName, "
          + "FIRST_NAME || ' ' || LAST_NAME AS empName "
          + "FROM DEPARTMENTS D, EMPLOYEES E "
          + "WHERE D.DEPARTMENT_ID=E.DEPARTMENT_ID OR E.DEPARTMENT_ID IS NULL "
          + "ORDER BY depName NULLS FIRST, empName";
          
}

