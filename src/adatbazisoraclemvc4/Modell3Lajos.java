package adatbazisoraclemvc4;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

public class Modell3Lajos extends Modell {
  
  public Modell3Lajos() {
    
  }
  
  @Override
  public DefaultTreeModel faModell() {
    kapcsolatNyit();
    DefaultMutableTreeNode faGyoker = new DefaultMutableTreeNode("Cég");
    DefaultTreeModel dtm = new DefaultTreeModel(faGyoker);
    try {
      ResultSet eredmeny=
        kapcsolat.createStatement().executeQuery(SQLRESZLEGEKDOLGOZOKLAJOS);
      boolean kezd = eredmeny.next();
      while (kezd) {
        String aktReszleg = eredmeny.getString("depName");
        DefaultMutableTreeNode reszlegNode = new DefaultMutableTreeNode(aktReszleg);
        faGyoker.add(reszlegNode);
        reszlegNode.add(new DefaultMutableTreeNode(eredmeny.getString("empName")));
        while((kezd = eredmeny.next()) && eredmeny.getString("depName").equals(aktReszleg)) {
          reszlegNode.add(new DefaultMutableTreeNode(eredmeny.getString("empName")));
        }
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    kapcsolatZar();
    return dtm;
  }
  
}
