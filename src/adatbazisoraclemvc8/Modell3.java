package adatbazisoraclemvc8;

import entities.Countries;
import entities.Departments;
import entities.Employees;
import entities.Jobs;
import entities.Locations;
import entities.Regions;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

public class Modell3 extends Modell {
  private List<Employees> list;

  public Modell3() {
    list = new Dao().getList();
  }
  
  
//  @PersistenceContext
//  public EntityManager em;
//  public EntityManagerFactory emf;
  
  @Override
  protected DefaultTreeModel faModell() {
    DefaultMutableTreeNode faGyoker = new DefaultMutableTreeNode("Cég");
    DefaultTreeModel dtm = new DefaultTreeModel(faGyoker);
//    emf = Persistence.createEntityManagerFactory("AdatbazisOraclePU");
//    em = emf.createEntityManager();
//    em.getTransaction().begin();
//    Query q = em.createQuery("SELECT e FROM Employees e LEFT JOIN FETCH e.departmentId d JOIN FETCH e.jobId JOIN FETCH d.locationId l JOIN FETCH l.countryId c JOIN FETCH c.regionId r ORDER BY d.departmentName NULLS FIRST, e.firstName, e.lastName");
//    List<Employees> list = q.getResultList();
//    em.getTransaction().commit();
//    em.close();
//    emf.close();
    int i = 0;
    while (i < list.size()) {
      Employees emp = list.get(i);
      Departments dep = emp.getDepartmentId();
      Jobs job = emp.getJobId();
      Locations loc = dep == null ? null : dep.getLocationId();
      Countries country = loc == null ? null : loc.getCountryId();
      Regions reg = country == null ? null : country.getRegionId();
      String aktReszleg = dep != null ? dep.getDepartmentName() : null;
      DefaultMutableTreeNode reszlegNode = new DefaultMutableTreeNode(aktReszleg != null ? aktReszleg : "Without department");
      faGyoker.add(reszlegNode);
      String empName = emp.getFirstName() + " " + emp.getLastName() +", " + job + ", " + reg + ", " + country + ", " + loc;
      reszlegNode.add(new DefaultMutableTreeNode(empName));
      String depName;
      while (++i < list.size() && 
              // a következő feltétel csak akkor igaz, ha depName is null és az aktReszleg is null!!!
              ((depName = (dep = (emp = list.get(i)).getDepartmentId()) != null ? dep.getDepartmentName() : null) == aktReszleg
              || (depName != null && depName.equals(aktReszleg)))) {
        reszlegNode.add(new DefaultMutableTreeNode(emp.getFirstName() + " " + emp.getLastName() + ", " + emp.getJobId() +
                dep.getLocationId().getCountryId().getRegionId() + ", " + dep.getLocationId().getCountryId() + ", " + dep.getLocationId()));
      }
    }
    
    return dtm;
    
  }
  
}
