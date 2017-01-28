package adatbazisoraclemvc4;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.TreeSet;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

public class Modell2 extends Modell {

  @Override
  protected DefaultTreeModel faModell() {
    kapcsolatNyit();
    DefaultMutableTreeNode faGyökér=
      new DefaultMutableTreeNode("Cég");
    DefaultTreeModel dtm=new DefaultTreeModel(faGyökér);
    try {
      TreeSet<String> részlegHalmaz=new TreeSet<>();
      ResultSet eredmény1=kapcsolat.createStatement().executeQuery(SQLRÉSZLEGEK);
      while(eredmény1.next())
        részlegHalmaz.add(eredmény1.getString("depName"));
      for(String részlegNév: részlegHalmaz) {
        //System.out.println(részlegNév);
        DefaultMutableTreeNode faRészleg=
          new DefaultMutableTreeNode(részlegNév);  
        PreparedStatement ps=kapcsolat.prepareStatement(
          SQLDOLGOZÓADOTTRÉSZLEGBŐL);
        ps.setString(1, részlegNév);
        ResultSet eredmény2=ps.executeQuery();
        while(eredmény2.next()) {
//          System.out.println(
//            "  "+eredmény2.getString("empName"));
          faRészleg.add(new DefaultMutableTreeNode(
            eredmény2.getString("empName")
          ));
        }
        faGyökér.add(faRészleg);
      }
    }
    catch(SQLException e) {
      e.printStackTrace();
    }
    kapcsolatZar();
    return dtm;
  }  
}
