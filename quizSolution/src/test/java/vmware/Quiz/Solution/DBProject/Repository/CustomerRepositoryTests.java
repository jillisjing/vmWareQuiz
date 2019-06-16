package vmware.Quiz.Solution.DBProject.Repository;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringRunner;

import vmware.Quiz.Solution.DBProject.bean.Customer;
import vmware.Quiz.Solution.DBProject.bean.VMService;
import vmware.Quiz.Solution.DBProject.repository.CustomerRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CustomerRepositoryTests extends AbstractTransactionalJUnit4SpringContextTests
{
  @Autowired
  private CustomerRepository customerRepository;
  private Customer _customer;
  
  private Set<VMService> _services;
  private String _cName = "zhang san";
  private String _sName = "service one";
  private Integer _age = new Integer( 20 );
  
  @Before
  public void setUp() throws Exception
  {
    _customer = new Customer();
    _customer.setAge( _age );
    _customer.setCustomerName( _cName );
    _services = new HashSet<>();
    VMService ser = new VMService();
    ser.setSerName( _sName );
    ser.setCreateDate( new Date() );
    _services.add( ser );
    _customer.setServices( _services );
  }
  
  @Test
  @Rollback(true)
  public void save()
  {
    Customer customerPersist = customerRepository.save( _customer );
    Assert.assertEquals( _age, customerPersist.getAge() );
    Assert.assertEquals( _cName, customerPersist.getCustomerName() );
    Set<VMService> serList = customerPersist.getServices();
    Assert.assertEquals( 1, serList.size() );
    Assert.assertEquals( _sName, serList.stream().findFirst().get().getSerName() );
  }
  
  @Test
  public void query() {
     customerRepository.deleteAll();
     customerRepository.save( _customer );
     List<Customer> lsc = customerRepository.findAll();
     Assert.assertEquals( 1, lsc.size() );
     Customer c = lsc.stream().findFirst().get();
     Assert.assertNotNull( c );
     Assert.assertEquals( _age, c.getAge() );
     Assert.assertEquals( _cName, c.getCustomerName() );
     Set<VMService> serList = c.getServices();
     Assert.assertEquals( 1, serList.size() );
     Assert.assertEquals( _sName, serList.stream().findFirst().get().getSerName() );
  }

}
