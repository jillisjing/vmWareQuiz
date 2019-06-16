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
import vmware.Quiz.Solution.DBProject.repository.VMServiceRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class VMServiceServiceTest extends AbstractTransactionalJUnit4SpringContextTests
{
  @Autowired
  private VMServiceService _vmService;
  
  @Autowired
  private VMServiceRepository _serviceRepository;
  
  private VMService _vm;
  private String _cName = "zhang san";
  private String _sName = "service one";
  private Integer _age = new Integer( 20 );
  private Set<Customer> _customers;

  @Before
  public void setUp() throws Exception
  {
    _vm = new VMService();
    _vm.setSerName( _sName );
    _vm.setCreateDate( new Date() );

    _customers = new HashSet<>();
    Customer cr = new Customer();
    cr.setAge( _age );
    cr.setCustomerName( _cName );
    _customers.add( cr );
    _vm.setCustomers( _customers );
  }
  
  @Test
  public void saveVMService()
  {
    VMService vmsps = _vmService.saveAndUpdate( _vm );
    Assert.assertEquals( _sName, vmsps.getSerName() );
    Set<Customer> cList = vmsps.getCustomers();
    Assert.assertEquals( 1, cList.size() );
    Assert.assertEquals( _cName, cList.stream().findFirst().get().getCustomerName() );
  }
  
  @Test
  public void updateVMService()
  {
    VMService vmsps = _vmService.saveAndUpdate( _vm );
    String newName = "service new Name";
    vmsps.setSerName( newName );
    VMService updateVm = _vmService.saveAndUpdate( vmsps );
    Assert.assertEquals( newName, updateVm.getSerName() );
  }
  
  @Test
  public void findByIdVMService()
  {
    VMService vmsps = _vmService.saveAndUpdate( _vm );
    VMService vms = _vmService.findById( vmsps.getId() );
    Assert.assertEquals( _sName, vms.getSerName() );
    Set<Customer> cList = vms.getCustomers();
    Assert.assertEquals( 1, cList.size() );
    Assert.assertEquals( _cName, cList.stream().findFirst().get().getCustomerName() );
  }
  
  @Test
  public void findAllVMService()
  {
    _serviceRepository.deleteAll();
    _vmService.saveAndUpdate( _vm );
    List<VMService> vms = _vmService.findAllVMServices();
    Assert.assertEquals( 1, vms.size() );
    VMService c = vms.stream().findFirst().get();
    Assert.assertNotNull( c );
    Assert.assertEquals( _sName, c.getSerName() );
    Set<Customer> cList = c.getCustomers();
    Assert.assertEquals( 1, cList.size() );
    Assert.assertEquals( _cName, cList.stream().findFirst().get().getCustomerName() );
  }
  
  @Test
  public void deleteVMServiceUsedCustomerFailed()
  {
    _vmService.saveAndUpdate( _vm );
    Assert.assertFalse( _vmService.deleteVMService( _vm ) );

    VMService vms = _vmService.findById( _vm.getId() );
    Assert.assertNotNull( vms );
  }
  
  @Test
  public void deleteVMServiceWithNullVMService()
  {
    Assert.assertFalse( _vmService.deleteVMService( _vm ) );
  }
  
  @Test
  public void deleteVMServiceSucceed()
  {
    _vm.setCustomers( null );
    _vmService.saveAndUpdate( _vm );
    Assert.assertTrue( _vmService.deleteVMService( _vm ) );
    VMService vms = _vmService.findById( _vm.getId() );
    Assert.assertNull( vms );

  }
}
