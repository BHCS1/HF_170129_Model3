package adatbazisoraclemvc2;

public class Start {
  public static void main(String[] args) {
    Modell modell=new Modell();
    Nézet nézet=new Nézet(modell);
    nézet.setVisible(true);
  }
}
