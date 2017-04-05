package adatbazisoraclemvc7;

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
//    Query q = em.createQuery("SELECT e FROM Employees e LEFT JOIN FETCH e.departmentId d ORDER BY d.departmentName NULLS FIRST, e.firstName, e.lastName");
    Query q = em.createQuery("SELECT distinct d FROM Departments d JOIN FETCH d.employeesList ORDER BY d.departmentName");
    List<Departments> list = q.getResultList();
    em.getTransaction().commit();
    em.close();
    emf.close();
    int i = 0;
    while (i < list.size()) {
      Departments aktReszleg = list.get(i);
      DefaultMutableTreeNode reszlegNode = new DefaultMutableTreeNode(aktReszleg.getDepartmentName());
//      System.out.println(aktReszleg.getDepartmentName());
      faGyoker.add(reszlegNode);
      for (Employees emp : aktReszleg.getEmployeesList()) {
        String empName = emp.getFirstName() + " " + emp.getLastName();
        reszlegNode.add(new DefaultMutableTreeNode(empName));
//        System.out.println("     " + empName);
      }
      i++;
    }
    
    return dtm;
    
  }
  
}
