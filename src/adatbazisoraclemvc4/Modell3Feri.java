/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adatbazisoraclemvc4;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

/**
 *
 * @author ferenc
 */
public class Modell3Feri extends Modell {

  @Override
  protected DefaultTreeModel faModell() {
    DefaultTreeModel faGyoker=null;
    try {
      DefaultMutableTreeNode node=new DefaultMutableTreeNode("Ceg");
      faGyoker=new DefaultTreeModel(node);
      kapcsolatNyit();
      System.out.println(SQLRESZLEGEKDOLGOZOK);
      ResultSet eredmeny=kapcsolat.createStatement().executeQuery(SQLRESZLEGEKDOLGOZOK);
      boolean kezd=eredmeny.next();
      while (kezd) {
        String aktReszleg=eredmeny.getString("depName");
        DefaultMutableTreeNode reszlegNode=new DefaultMutableTreeNode(aktReszleg);
        while (kezd && eredmeny.getString("depName").equals(aktReszleg)) {
          reszlegNode.add(new DefaultMutableTreeNode(eredmeny.getString("empName")));
          kezd=eredmeny.next();
        }
        node.add(reszlegNode);
      }
      kapcsolatZar();
    } catch (SQLException ex) {
      Logger.getLogger(Modell3.class.getName()).log(Level.SEVERE, null, ex);
    }
    return faGyoker;
  }
}
