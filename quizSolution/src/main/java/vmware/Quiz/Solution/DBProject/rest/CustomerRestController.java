package vmware.Quiz.Solution.DBProject.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import vmware.Quiz.Solution.DBProject.bean.Customer;
import vmware.Quiz.Solution.DBProject.service.CustomerService;

@Controller
@RequestMapping( "/vmware/customers" )
public class CustomerRestController
{
  @Autowired
  CustomerService customerService;

  @RequestMapping( value = "/getAll", method = RequestMethod.GET )
  @ResponseBody
  public List<Customer> getCustomers()
  {
    return customerService.findAllCustomers();
  }

  @RequestMapping( value = "/delete", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE )
  public void deleteCustomers( @RequestBody Customer customer )
  {
    customerService.deleteCustomer( customer );
  }

  @RequestMapping( value = "/createOrUpdate", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE )
  @ResponseBody
  public Customer createOrUpdateCustomers( @RequestBody Customer customer )
  {
    return customerService.saveAndUpdate( customer );
  }

}
