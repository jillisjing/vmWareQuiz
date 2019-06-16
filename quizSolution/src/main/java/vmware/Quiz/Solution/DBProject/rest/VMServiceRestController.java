package vmware.Quiz.Solution.DBProject.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import vmware.Quiz.Solution.DBProject.bean.VMService;
import vmware.Quiz.Solution.DBProject.service.VMServiceService;

@Controller
@RequestMapping("/vmware/vmservice")
public class VMServiceRestController
{
  @Autowired
  VMServiceService vmServiceService;

  @RequestMapping( value = "", method = RequestMethod.GET )
  @ResponseBody
  public List<VMService> getVMService()
  {
    return vmServiceService.findAllVMServices();
  }

  @RequestMapping( value = "", method = RequestMethod.DELETE )
  public void deleteCustomers( VMService vmservice )
  {
    vmServiceService.deleteVMService( vmservice );
  }

  @RequestMapping( value = "", method = RequestMethod.POST )
  public void createOrUpdateCustomers( VMService vmservice )
  {
    vmServiceService.saveAndUpdate( vmservice );
  }

}
