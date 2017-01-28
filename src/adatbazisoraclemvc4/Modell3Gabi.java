
package adatbazisoraclemvc4;

import static adatbazisoraclemvc4.Adatok.SQLRESZLEGEKDOLGOZOKLAJOS;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

public class Modell3Gabi extends Modell {

  @Override
  protected DefaultTreeModel faModell() {
    kapcsolatNyit();
    DefaultMutableTreeNode faGyoker = new DefaultMutableTreeNode("Cég");
    DefaultTreeModel dtm = new DefaultTreeModel(faGyoker);
    try {
      ResultSet eredmeny=
        kapcsolat.createStatement().executeQuery(SQLRESZLEGEKDOLGOZOK);
      String aktReszleg = null;
      DefaultMutableTreeNode reszlegNode = null;
      while (eredmeny.next()) {
        if (aktReszleg == null || !aktReszleg.equals(eredmeny.getString("depName"))) {
          aktReszleg = eredmeny.getString("depName");
          reszlegNode = new DefaultMutableTreeNode(aktReszleg!=null?aktReszleg:"Részleg nélküli");
          faGyoker.add(reszlegNode);
        }
        reszlegNode.add(new DefaultMutableTreeNode(eredmeny.getString("empName")));
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    kapcsolatZar();
    return dtm;
    
  }
  
}
