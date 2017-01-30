
package adatbazisoraclemvc4;

import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

public class Modell3 extends Modell {

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
        String depName = eredmeny.getString("depName");
        if (reszlegNode == null || (depName!=null && !depName.equals(aktReszleg))) {
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
