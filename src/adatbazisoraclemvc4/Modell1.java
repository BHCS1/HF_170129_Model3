package adatbazisoraclemvc4;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

public class Modell1 extends Modell {
  
  class ReszlegDolgozo {
    public String részlegNév, dolgozóNév;

    public ReszlegDolgozo(String részlegNév, String dolgozóNév) {
      this.részlegNév = részlegNév;
      this.dolgozóNév = dolgozóNév;
    }    
  }
  //String s=DRIVER;

  public Modell1() {
    //super();
  }
  
  private ArrayList<ReszlegDolgozo> listaKészít() {
    ArrayList<ReszlegDolgozo> lista=new ArrayList<>();
    try {
      ResultSet eredmény=kapcsolat.createStatement().
        executeQuery(SQLRÉSZLEGDOLGOZÓ);
      while(eredmény.next()) {
        String részleg=eredmény.getString("depName");
        String dolgozó=eredmény.getString("empName");
        ReszlegDolgozo rd=new ReszlegDolgozo(részleg, dolgozó);
        lista.add(rd);
      }
    }
    catch(SQLException e) {
      e.printStackTrace();
    }
    return lista;
  }
  
  @Override
  public DefaultTreeModel faModell() {
    kapcsolatNyit();
    ArrayList<ReszlegDolgozo> lista=listaKészít();
    kapcsolatZar();
    DefaultMutableTreeNode faGyökér=
      new DefaultMutableTreeNode("Cég");
    DefaultTreeModel dtm=new DefaultTreeModel(faGyökér);
    int i=0;
    while(i<lista.size()) {
      String aktRészleg=lista.get(i).részlegNév;
      DefaultMutableTreeNode faRészleg=
        new DefaultMutableTreeNode(aktRészleg);
      while(i<lista.size() &&
          lista.get(i).részlegNév.equals(aktRészleg)) {
        faRészleg.add(new DefaultMutableTreeNode(
          lista.get(i).dolgozóNév
        ));
        i++;
      }
      faGyökér.add(faRészleg);
    }
    return dtm;
  }

}
