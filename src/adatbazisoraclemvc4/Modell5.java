package adatbazisoraclemvc4;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

public class Modell5 extends Modell {

  private ArrayList<ReszlegDolgozo> lista = new ArrayList<>();
  private String htmlHead
          = "<head>\n"
          + "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n"
          + "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />\n"
          + "<link rel=\"stylesheet\" href=\"https://code.jquery.com/mobile/1.4.5/jquery.mobile-1.4.5.min.css\">\n"
          + "<script src=\"https://code.jquery.com/jquery-1.11.3.min.js\"></script>\n"
          + "<script src=\"https://code.jquery.com/mobile/1.4.5/jquery.mobile-1.4.5.min.js\"></script>\n"
          + "</head>";

  public Modell5() {
    File htmlFile = new File("./files/index.html");
    htmlTartalmatKeszit(htmlFile);
  }

  private void htmlTartalmatKeszit(File htmlFile) {
    kapcsolatNyit();
    StringBuilder html = new StringBuilder("<!DOCTYPE html> +\n");
    html.append("<html>\n");
    html.append(htmlHead);
    html.append("<body>\n");
    html.append("<div data-role=\"page\" id=\"pageone\">\n"
            + "  <div data-role=\"header\">\n"
            + "    <h1>Collapsible Lists</h1>\n"
            + "  </div>\n\n"
            + "  <div data-role=\"main\" class=\"ui-content\">\n"
            + "   <h2>HR nyilvántartás</h2>\n");
    try {
      ResultSet eredmeny
              = kapcsolat.createStatement().executeQuery(SQLRESZLEGEKDOLGOZOK);
      boolean kezd = eredmeny.next();
      while (kezd) {
        String aktReszleg = eredmeny.getString("depName");
        html.append("   <div data-role=\"collapsible\">\n"
                + "    <h4>");
        html.append(aktReszleg != null ? aktReszleg : "Without department");
        html.append("</h4>\n"
                + "    <ul data-role=\"listview\">\n");
        while (kezd && (eredmeny.getString("depName") == aktReszleg || eredmeny.getString("depName").equals(aktReszleg))) {
          html.append("     <li><a href=\"#\">");
          html.append(eredmeny.getString("empName"));
          html.append("</a></li>\n");
          kezd = eredmeny.next();
        }
        html.append("    </ul>\n"
                + "   </div>\n");
      }
    }
    catch (SQLException e) {
      e.printStackTrace();
    }

    html.append("</div>\n"
            + "\n"
            + "  <div data-role=\"footer\">\n"
            + "    <h1>Insert Footer Text Here</h1>\n"
            + "  </div>\n"
            + "</div> \n"
            + "\n"
            + "</body>\n"
            + "</html>");
    kapcsolatZar();
    System.out.println(html.toString());
    try {
      FileWriter fr = new FileWriter(htmlFile);
      fr.write(html.toString());
//      fr.flush();
      fr.close();
    }
    catch (IOException ex) {
      ex.printStackTrace();
    }

  }

  @Override
  public DefaultTreeModel faModell() {
    kapcsolatNyit();
    DefaultMutableTreeNode faGyoker = new DefaultMutableTreeNode("Cég");
    DefaultTreeModel dtm = new DefaultTreeModel(faGyoker);
    try {
      ResultSet eredmeny=
        kapcsolat.createStatement().executeQuery(SQLRESZLEGEKDOLGOZOK);
      boolean kezd = eredmeny.next();
      while (kezd) {
        String aktReszleg = eredmeny.getString("depName");
        DefaultMutableTreeNode reszlegNode = new DefaultMutableTreeNode(aktReszleg != null ? aktReszleg : "Without department");
        faGyoker.add(reszlegNode);
        reszlegNode.add(new DefaultMutableTreeNode(eredmeny.getString("empName")));
        while((kezd = eredmeny.next()) && (eredmeny.getString("depName") == aktReszleg || eredmeny.getString("depName").equals(aktReszleg))) {
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
