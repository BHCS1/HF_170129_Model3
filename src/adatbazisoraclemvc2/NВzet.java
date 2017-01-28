package adatbazisoraclemvc2;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Nézet extends JFrame
        implements ActionListener {

  private final Modell MODELL; //=null;
  private JComboBox cbMűvelet;
  private JTextField tfFizetés;
  private JList lDolgozók = new JList();
  private JLabel lbÁllapot;

  public Nézet(Modell modell) {
    this.MODELL=modell;
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
    //setVisible(true);
    tfFizetés.requestFocus();
  }
  
  @Override
  public void actionPerformed(ActionEvent e) {
    try {
      DefaultListModel dlm = //Modell.getDolgozóLista(
        MODELL.getDolgozóLista(
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

//  public static void main(String[] args) {
//    new Nézet();
//  }
}

