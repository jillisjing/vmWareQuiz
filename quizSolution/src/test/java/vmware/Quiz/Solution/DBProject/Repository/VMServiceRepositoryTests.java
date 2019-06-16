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
import vmware.Quiz.Solution.DBProject.repository.VMServiceRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class VMServiceRepositoryTests extends AbstractTransactionalJUnit4SpringContextTests
{
  @Autowired
  private VMServiceRepository _serviceRepository;
  private VMService _service;
  
  private Set<Customer> _customers;
  private String _cName = "zhang san";
  private String _sName = "service one";
  private Integer _age = new Integer( 20 );
  
  @Before
  public void setUp() throws Exception
  {
    _service = new VMService();
    _service.setSerName( _sName );
    _service.setCreateDate( new Date() );

    _customers = new HashSet<>();
    Customer cr = new Customer();
    cr.setAge( _age );
    cr.setCustomerName( _cName );
    _customers.add( cr );
    _service.setCustomers( _customers );
  }
  
  @Test
  @Rollback(true)
  public void save()
  {
    VMService servicePersist = _serviceRepository.save( _service );
    Assert.assertEquals( _sName, servicePersist.getSerName() );
    Set<Customer> cList = servicePersist.getCustomers();
    Assert.assertEquals( 1, cList.size() );
    Assert.assertEquals( _cName, cList.stream().findFirst().get().getCustomerName() );
  }
  
  @Test
  public void query()
  {
    _serviceRepository.deleteAll();
    _serviceRepository.save( _service );
    List<VMService> lss = _serviceRepository.findAll();
    Assert.assertEquals( 1, lss.size() );
    VMService c = lss.stream().findFirst().get();
    Assert.assertNotNull( c );
    Assert.assertEquals( _sName, c.getSerName() );
    Set<Customer> cList = c.getCustomers();
    Assert.assertEquals( 1, cList.size() );
    Assert.assertEquals( _cName, cList.stream().findFirst().get().getCustomerName() );
  }

}
