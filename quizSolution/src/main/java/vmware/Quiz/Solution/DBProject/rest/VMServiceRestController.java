package vmware.Quiz.Solution.DBProject.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import vmware.Quiz.Solution.DBProject.bean.VMService;
import vmware.Quiz.Solution.DBProject.service.VMServiceService;

@Controller
@RequestMapping( "/vmware/vmservice" )
public class VMServiceRestController
{
  @Autowired
  VMServiceService vmServiceService;

  @RequestMapping( value = "/getAll", method = RequestMethod.GET )
  @ResponseBody
  public List<VMService> getVMService()
  {
    return vmServiceService.findAllVMServices();
  }

  @RequestMapping( value = "/delete", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE )
  public void deleteCustomers( @RequestBody VMService vmservice )
  {
    vmServiceService.deleteVMService( vmservice );
  }

  @RequestMapping( value = "/createOrUpdate", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE )
  @ResponseBody
  public VMService createOrUpdateCustomers( @RequestBody VMService vmservice )
  {
    return vmServiceService.saveAndUpdate( vmservice );
  }

}
