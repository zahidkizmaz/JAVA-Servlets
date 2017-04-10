
package pl.polsl.report2;

import java.util.List;
import javax.ejb.Local;


/**
 * Here we specify all the methods that will be used
 * @author Kizmaz Zahid - Hernandez Raul
 * @version 1.0
 */
@Local
public interface Report3SesionBeanLocal {
    
    public List<Airplane> findAll();
    public Airplane update(Airplane a);
    public void delete(Airplane a);
    public Airplane insert(Airplane a);
    public Airplane find(Integer id);
    public List<Customer> findAllCustomer();
    public Customer findC(Integer id);
    public Customer insert(Customer c);
    public void deleteC(Customer c);
    public Customer updateC(Customer c);
}
