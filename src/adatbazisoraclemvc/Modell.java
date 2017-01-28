package adatbazisoraclemvc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.DefaultListModel;

public class Modell implements Adatok {

  private Modell() {
    ;
  }
  
  public static DefaultListModel getDolgozóLista(
          String művelet, String fizetés)
          throws IllegalArgumentException {
    try {
      int fiz = Integer.parseInt(fizetés);
      if (fiz < 0) {
        throw new IllegalArgumentException("A fizetés negatív.");
      }
    }
    catch (NumberFormatException nfe) {
      throw new NumberFormatException("A fizetés nem pozitív egész szám.");
    }
      
    String sql
            = "SELECT FIRST_NAME || ' ' || LAST_NAME AS empName "
            + "FROM EMPLOYEES "
            + "WHERE SALARY " + művelet + fizetés + " "
            + "ORDER BY FIRST_NAME, LAST_NAME";
    //lbÁllapot.setText(sql);
    DefaultListModel dlm = new DefaultListModel();
    try {
      Class.forName(DRIVER);
      Connection kapcsolat = DriverManager.getConnection(
              URL, USERNAME, PASSWORD);
      ResultSet eredmény
              = kapcsolat.createStatement().executeQuery(sql);
      while (eredmény.next()) {
        dlm.addElement(eredmény.getString("empName"));
      }
      kapcsolat.close();
    } catch (ClassNotFoundException | SQLException e) {
      ;
    }
    return dlm;
  }
}
