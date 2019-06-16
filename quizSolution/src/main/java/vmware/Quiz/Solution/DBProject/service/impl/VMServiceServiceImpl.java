package vmware.Quiz.Solution.DBProject.service.impl;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vmware.Quiz.Solution.DBProject.bean.VMService;
import vmware.Quiz.Solution.DBProject.repository.VMServiceRepository;
import vmware.Quiz.Solution.DBProject.service.VMServiceService;

@Service
public class VMServiceServiceImpl implements VMServiceService
{
  @Autowired
  VMServiceRepository repository;
  
  @Override
  public VMService findById( int id )
  {
    VMService vms = null;
    try
    {
      vms = repository.findById( id ).get();
    }
    catch ( NoSuchElementException e )
    {
      //ignore
    }
    return vms;
  }

  @Override
  public List<VMService> findAllVMServices()
  {
    return repository.findAll();
  }

  @Override
  public VMService saveAndUpdate( VMService vmService )
  {
    if ( vmService == null )
    {
      return null;
    }
    return repository.saveAndFlush( vmService );
  }

  @Override
  public boolean deleteVMService( VMService vmService )
  {
    if ( vmService == null || vmService.getId() == null )
    {
      return false;
    }
    VMService ser = repository.findById( vmService.getId() ).get();

    if ( ser.getCustomers() != null )
    { // service in used, cannot delete
      return false;
    }
    repository.delete( ser );
    return true;
  }

}
