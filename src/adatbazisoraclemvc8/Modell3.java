package adatbazisoraclemvc8;

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
    Query q = em.createQuery("SELECT e FROM Employees e LEFT JOIN FETCH e.departmentId d ORDER BY d.departmentName NULLS FIRST, e.firstName, e.lastName");
    List<Employees> list = q.getResultList();
    em.getTransaction().commit();
    em.close();
    emf.close();
    int i = 0;
    while (i < list.size()) {
      String aktReszleg = list.get(i).getDepartmentId() != null ? list.get(i).getDepartmentId().getDepartmentName() : null;
      DefaultMutableTreeNode reszlegNode = new DefaultMutableTreeNode(aktReszleg != null ? aktReszleg : "Without department");
      faGyoker.add(reszlegNode);
      String empName = list.get(i).getFirstName() + " " + list.get(i).getLastName();
      reszlegNode.add(new DefaultMutableTreeNode(empName));
      String depName;
      while (++i < list.size() && 
              // a következő feltétel csak akkor igaz, ha depName is null és az aktReszleg is null!!!
              ((depName = list.get(i).getDepartmentId() != null ? list.get(i).getDepartmentId().getDepartmentName() : null) == aktReszleg
              || (depName != null && depName.equals(aktReszleg)))) {
        reszlegNode.add(new DefaultMutableTreeNode(list.get(i).getFirstName() + " " + list.get(i).getLastName()));
      }
    }
    
    return dtm;
    
  }
  
}
