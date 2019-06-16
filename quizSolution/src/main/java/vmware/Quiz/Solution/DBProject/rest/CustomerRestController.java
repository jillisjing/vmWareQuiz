package vmware.Quiz.Solution.DBProject.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import vmware.Quiz.Solution.DBProject.bean.Customer;
import vmware.Quiz.Solution.DBProject.service.CustomerService;

@Controller
@RequestMapping("/vmware/customers")
public class CustomerRestController
{
  @Autowired
  CustomerService customerService;
  
  @RequestMapping( value = "", method = RequestMethod.GET )
  @ResponseBody
  public List<Customer> getCustomers()
  {
    return customerService.findAllCustomers();
  }
  
  @RequestMapping(value = "", method = RequestMethod.DELETE)
  public void deleteCustomers(Customer customer) {
    customerService.deleteCustomer( customer );
  }
  
  @RequestMapping(value = "", method = RequestMethod.POST)
  public void createOrUpdateCustomers(Customer customer) {
    customerService.saveAndUpdate( customer );
  }
  
}
