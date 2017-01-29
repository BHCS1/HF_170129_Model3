package adatbazisoracle;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

public class HRLekérdez1 extends JFrame
        implements ActionListener {

  private JComboBox cbMűvelet;
  private JTextField tfFizetés;
  private JList lDolgozók = new JList();
  private JLabel lbÁllapot;

  public HRLekérdez1() {
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    setTitle("HR Lekérdező 1.0");
    setSize(400, 300);
    JPanel pnFelső = new JPanel();
    pnFelső.add(new JLabel("Fizetés "));
    cbMűvelet = new JComboBox(
            new String[]{"<=", "<", "=", ">", ">=", "<>"});
    pnFelső.add(cbMűvelet);
    tfFizetés = new JTextField(6);
    pnFelső.add(tfFizetés);
    JButton btLekérdez = new JButton("Lekérdez");
    btLekérdez.addActionListener(this);
    pnFelső.add(btLekérdez);
    add(pnFelső, BorderLayout.NORTH);
    add(new JScrollPane(lDolgozók));
    add(lbÁllapot = new JLabel(" "), BorderLayout.SOUTH);
    setVisible(true);
    tfFizetés.requestFocus();
  }

//  private DefaultListModel getDolgozóLista(
//      String művelet, String fizetés) {
//    try {
//      if (Integer.parseInt(fizetés) < 0) {
//        throw new NumberFormatException("mínusz ez");
//      }
//    }
//    catch (NumberFormatException nfe) {
//      return new DefaultListModel();
//    }
//    String sql = 
//      "SELECT FIRST_NAME || ' ' || LAST_NAME AS empName " +
//      "FROM EMPLOYEES " +
//      "WHERE SALARY " + művelet + fizetés + " " +
//      "ORDER BY FIRST_NAME, LAST_NAME";
//    //lbÁllapot.setText(sql);
//    DefaultListModel dlm = new DefaultListModel();
//    try {
//      Class.forName("oracle.jdbc.driver.OracleDriver");
//      Connection kapcsolat=DriverManager.getConnection(
//        "jdbc:oracle:thin:@localhost:1521:xe",
//        "HR", "hr");
//      ResultSet eredmény=
//        kapcsolat.createStatement().executeQuery(sql);
//      while(eredmény.next()) {
//        dlm.addElement(eredmény.getString("empName"));
//      }
//      kapcsolat.close();
//    }
//    catch(ClassNotFoundException | SQLException e) {
//      ;
//    }
//    return dlm;
//  }
  private DefaultListModel getDolgozóLista(
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
      Class.forName("oracle.jdbc.driver.OracleDriver");
      Connection kapcsolat = DriverManager.getConnection(
              "jdbc:oracle:thin:@localhost:1521:xe",
              "HR", "hr");
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

  @Override
  public void actionPerformed(ActionEvent e) {
    //dlm.addElement("Sárga elefánt++");
    try {
      DefaultListModel dlm = getDolgozóLista(
              (String) cbMűvelet.getSelectedItem(),
              tfFizetés.getText());
      lDolgozók.setModel(dlm);
      lbÁllapot.setText("Fizetés " + 
        (String) cbMűvelet.getSelectedItem() + " " +
        tfFizetés.getText() + ", " + dlm.getSize() + " fő");
    } 
    catch (IllegalArgumentException iae) {
      JOptionPane.showMessageDialog(
              this, "<html>Hiba:<br/>" + iae.getMessage()
              + "</html>", "Hibaüzenet", JOptionPane.ERROR_MESSAGE);
    }
  }

  public static void main(String[] args) {
    new HRLekérdez1();
  }
}
