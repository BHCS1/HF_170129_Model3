package adatbazisoraclemvc6;

import entities.Departments;
import entities.Employees;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

public class Modell3 extends Modell {
  
  @PersistenceContext
  public EntityManager em;
  public EntityManagerFactory emf;
  
  @Override
  protected DefaultTreeModel faModell() {
    DefaultMutableTreeNode faGyoker = new DefaultMutableTreeNode("Cég");
    DefaultTreeModel dtm = new DefaultTreeModel(faGyoker);
    emf = Persistence.createEntityManagerFactory("AdatbazisOraclePU");
    em = emf.createEntityManager();
    em.getTransaction().begin();
    Query q = em.createQuery("SELECT e, d FROM Employees e LEFT JOIN e.departmentId d ORDER BY d.departmentName NULLS FIRST, e.firstName, e.lastName");
    List<Object[]> list = q.getResultList();
    em.getTransaction().commit();
    em.close();
    emf.close();
    int i = 0;
    while (i < list.size()) {
      String aktReszleg = list.get(i)[1] != null ? ((Departments) list.get(i)[1]).getDepartmentName() : null;
      DefaultMutableTreeNode reszlegNode = new DefaultMutableTreeNode(aktReszleg != null ? aktReszleg : "Without department");
      faGyoker.add(reszlegNode);
      String empName = ((Employees) list.get(i)[0]).getFirstName() + " " + ((Employees) list.get(i)[0]).getLastName();
      reszlegNode.add(new DefaultMutableTreeNode(empName));
      String depName;
      while (++i < list.size() && ((depName = list.get(i)[1] != null ? ((Departments) list.get(i)[1]).getDepartmentName() : null) == aktReszleg
              || (depName != null && depName.equals(aktReszleg)))) {
        reszlegNode.add(new DefaultMutableTreeNode(((Employees) list.get(i)[0]).getFirstName() + " " + ((Employees) list.get(i)[0]).getLastName()));
      }
    }
    
    return dtm;
    
  }
  
}
