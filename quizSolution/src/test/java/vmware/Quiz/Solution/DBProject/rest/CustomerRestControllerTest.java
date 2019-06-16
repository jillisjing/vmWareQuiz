package vmware.Quiz.Solution.DBProject.rest;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;

import org.assertj.core.util.Lists;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import vmware.Quiz.Solution.DBProject.bean.Customer;
import vmware.Quiz.Solution.DBProject.service.CustomerService;

@RunWith( SpringRunner.class )
@SpringBootTest( webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT )
public class CustomerRestControllerTest
{

  @Autowired
  private TestRestTemplate restTemplate;

  @Before
  public void setUp() throws Exception
  {
    CustomerService customerService = mock( CustomerService.class );
    List<Customer> cts = Lists.newArrayList();
    when( customerService.findAllCustomers() ).thenReturn( cts );
  }

  @Test
  @SuppressWarnings( "unchecked" )
  public void testGetCustomers() throws Exception
  {
    List<Customer> response = restTemplate.getForObject( "/vmware/customers/getAll", List.class );
    Assert.assertNotNull( response );
  }

  @Test
  @SuppressWarnings( "unchecked" )
  public void testCreateOrUpdateCustomers() throws Exception
  {
    List<Customer> response1 = restTemplate.getForObject( "/vmware/customers/getAll", List.class );

    Customer customer = new Customer();
    customer.setAge( 22 );
    customer.setCustomerName( "test" );
    Customer resCustomer = restTemplate.postForObject( "/vmware/customers/createOrUpdate", customer, Customer.class );
    Assert.assertNotNull( resCustomer.getId() );

    List<Customer> response2 = restTemplate.getForObject( "/vmware/customers/getAll", List.class );
    Assert.assertEquals( response1.size() + 1, response2.size() );

    restTemplate.postForObject( "/vmware/customers/delete", resCustomer, String.class );

    List<Customer> response3 = restTemplate.getForObject( "/vmware/customers/getAll", List.class );
    Assert.assertEquals( response1.size(), response3.size() );
  }

}
