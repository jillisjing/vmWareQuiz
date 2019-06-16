package vmware.Quiz.Solution.DBProject.service;

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
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringRunner;

import vmware.Quiz.Solution.DBProject.bean.Customer;
import vmware.Quiz.Solution.DBProject.bean.VMService;
import vmware.Quiz.Solution.DBProject.repository.CustomerRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CustomerServiceTest extends AbstractTransactionalJUnit4SpringContextTests
{

  @Autowired
  private CustomerService _cmService;
  
  @Autowired
  private CustomerRepository _customerRepository;
  
  private Customer _cm;
  private String _cName = "zhang san";
  private String _sName = "service one";
  private Integer _age = new Integer( 20 );
  private Set<VMService> _vmSers;

  @Before
  public void setUp() throws Exception
  {
    _cm = new Customer();
    _cm.setAge( _age );
    _cm.setCustomerName( _cName );
    
    VMService vmSer = new VMService();
    vmSer.setSerName( _sName );
    vmSer.setCreateDate( new Date() );
    
    _vmSers = new HashSet<>();
    _vmSers.add( vmSer );
    _cm.setServices(  _vmSers );
  }
  
  @Test
  public void saveVMService()
  {
    Customer cmsps = _cmService.saveAndUpdate( _cm );
    Assert.assertEquals( _cName, cmsps.getCustomerName() );
    Set<VMService> sList = cmsps.getServices();
    Assert.assertEquals( 1, sList.size() );
    Assert.assertEquals( _sName, sList.stream().findFirst().get().getSerName() );
  }
  
  @Test
  public void updateVMService()
  {
    Customer cmsps = _cmService.saveAndUpdate( _cm );
    String newName = "customer new Name";
    cmsps.setCustomerName( newName );
    Customer updateCm = _cmService.saveAndUpdate( cmsps );
    Assert.assertEquals( newName, updateCm.getCustomerName() );
  }
  
  @Test
  public void findByIdVMService()
  {
    Customer cm = _cmService.saveAndUpdate( _cm );
    Customer cms = _cmService.findById( cm.getId() );
    Assert.assertEquals( _cName, cms.getCustomerName() );
    Set<VMService> cList = cms.getServices();
    Assert.assertEquals( 1, cList.size() );
    Assert.assertEquals( _sName, cList.stream().findFirst().get().getSerName() );
  }
  
  @Test
  public void findAllVMService()
  {
    _customerRepository.deleteAll();
    _cmService.saveAndUpdate( _cm );
    List<Customer> cms = _cmService.findAllCustomers();
    Assert.assertEquals( 1, cms.size() );
    Customer c = cms.stream().findFirst().get();
    Assert.assertNotNull( c );
    Assert.assertEquals( _age, c.getAge() );
    Set<VMService> cList = c.getServices();
    Assert.assertEquals( 1, cList.size() );
    Assert.assertEquals( _sName, cList.stream().findFirst().get().getSerName() );
  }
  
  
  @Test
  public void deleteVMServiceWithNullVMService()
  {
    Assert.assertFalse( _cmService.deleteCustomer( _cm ) );
  }
  
  @Test
  public void deleteVMServiceSucceed()
  {
    _cm.setServices( null );
    _cmService.saveAndUpdate( _cm );
    Assert.assertTrue( _cmService.deleteCustomer( _cm ) );
    Customer vms = _cmService.findById( _cm.getId() );
    Assert.assertNull( vms );

  }

}
