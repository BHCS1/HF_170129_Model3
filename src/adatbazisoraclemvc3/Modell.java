package adatbazisoraclemvc3;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Modell implements Adatok {
  private ArrayList<String> részlegLista=new ArrayList<>();
  private ArrayList<String> dolgozóLista=new ArrayList<>();
  
  public Modell() {
    részlegLista=részlegListaKészít();
    dolgozóLista=dolgozóListaKészít(null);
  }
  
  private ArrayList<String> részlegListaKészít() {
    ArrayList<String> lista=new ArrayList<>();
//    lista.add("r1");
//    lista.add("r2");
    try {
      Class.forName(DRIVER);
      Connection kapcsolat = DriverManager.getConnection(
        URL, USERNAME, PASSWORD);
      ResultSet eredmény=
        kapcsolat.createStatement().executeQuery(SQLRÉSZLEGEK);
      while (eredmény.next()) {
        lista.add(eredmény.getString("depName"));
      }
      kapcsolat.close();
    } catch (ClassNotFoundException | SQLException e) {
      ;
    }
    return lista;
  }

  public ArrayList<String> getRészlegLista() {
    return részlegLista;
  }
  
  /*private*/ public ArrayList<String> dolgozóListaKészít(
      ArrayList<String> részlegLista) {
    if(részlegLista==null)
      return new ArrayList<String>();
//    System.out.println(
//      "(DEPARTMENT_NAME='"+
//      String.join("' OR DEPARTMENT_NAME='", részlegLista)+
//    "')");
    String SQLDOLGOZÓK2=
      " AND \n  (DEPARTMENT_NAME='"+
      String.join("' OR DEPARTMENT_NAME='", részlegLista)+
      "')";
    String SQL=
      SQLDOLGOZÓK1+
      SQLDOLGOZÓK2+"\n"+
      SQLDOLGOZÓK3;
    //System.out.println(SQL);
//    ArrayList<String> lista=dolgozóLista; //new ArrayList<>();
//    lista.add("d1");
//    lista.add("d2");
//    lista.add("d3");
    dolgozóLista.clear();
//    dolgozóLista.add("d1");
//    dolgozóLista.add("d2");
    try {
      Class.forName(DRIVER);
      Connection kapcsolat = DriverManager.getConnection(
        URL, USERNAME, PASSWORD);
      ResultSet eredmény=
        kapcsolat.createStatement().executeQuery(SQL);
      while (eredmény.next()) {
        dolgozóLista.add(eredmény.getString("empName"));
      }
      kapcsolat.close();
    } catch (ClassNotFoundException | SQLException e) {
      ;
    }
    return dolgozóLista; //lista;
  }

  public ArrayList<String> getDolgozóLista() {
    return dolgozóLista;
  }
  
  
  
}
