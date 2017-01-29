package adatbazisoraclemvc6;

public class Start {

  public static void main(String[] args) {
//    Modell modell=new Modell1();
//    Modell modell=new Modell2();
//    Modell modell=new Modell3Lajos();
//    Modell modell= new Modell3Gabi();
    Modell modell = new Modell3();
    new Nezet(modell).setVisible(true);
  }

}
