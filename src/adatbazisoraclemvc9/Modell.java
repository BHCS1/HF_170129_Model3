package adatbazisoraclemvc9;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.tree.DefaultTreeModel;

public abstract class Modell implements Adatok {

  protected Connection kapcsolat = null;

  //private Modell() {
//  protected Modell() {
//    ;
//  }
  protected void kapcsolatNyit() {
    try {
      Class.forName(DRIVER);
      kapcsolat = DriverManager.getConnection(
              URL, USERNAME, PASSWORD);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  protected void kapcsolatZar() {
    try {
      if (kapcsolat != null) //==isClosed()
      {
        kapcsolat.close();
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  protected abstract DefaultTreeModel faModell();
}
