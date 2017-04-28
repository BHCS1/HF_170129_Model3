package adatbazisoraclemvc8;

import entities.Employees;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author lajos
 */
public class Dao {
  @PersistenceContext
  public EntityManager em;
  public EntityManagerFactory emf;
  
  public List<Employees> getList() {
    emf = Persistence.createEntityManagerFactory("AdatbazisOraclePU");
    em = emf.createEntityManager();
    em.getTransaction().begin();
    Query q = em.createQuery("SELECT e FROM Employees e LEFT JOIN FETCH e.departmentId d JOIN FETCH e.jobId j JOIN FETCH d.locationId l JOIN FETCH l.countryId c JOIN FETCH c.regionId r ORDER BY d.departmentName NULLS FIRST, e.firstName, e.lastName");
    List<Employees> list = q.getResultList();
    em.getTransaction().commit();
    em.close();
    emf.close();
    return list;
  }
  
}
