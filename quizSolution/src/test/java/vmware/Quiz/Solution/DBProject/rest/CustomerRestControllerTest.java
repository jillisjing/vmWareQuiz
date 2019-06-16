package vmware.Quiz.Solution.DBProject.rest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import vmware.Quiz.Solution.DBProject.bean.Customer;
import vmware.Quiz.Solution.DBProject.service.CustomerService;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@WebMvcTest(CustomerRestController.class)
public class CustomerRestControllerTest
{
  @Autowired
  private MockMvc _mvc;
  
  @MockBean
  CustomerService customerService;
  
  @Before
  public void setUp() throws Exception { 
    _mvc = MockMvcBuilders.standaloneSetup( new CustomerRestController() ).build();
  }
  
  @Test
  public void testGetCustomers() throws Exception { 
    List<Customer> cts = new ArrayList<>();
    when(customerService.findAllCustomers()).thenReturn(cts);
   String request = _mvc.perform( get( "/vmware/customers" )
                                  .contentType( MediaType.APPLICATION_JSON_UTF8 ) )
                                  .andExpect( status().isOk())
                                  .andReturn().getResponse().getContentAsString();
   
   System.out.println( request );
  }
}
