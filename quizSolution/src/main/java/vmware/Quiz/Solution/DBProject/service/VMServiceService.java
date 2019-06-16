package vmware.Quiz.Solution.DBProject.service;

import java.util.List;

import vmware.Quiz.Solution.DBProject.bean.VMService;

public interface VMServiceService
{
  /**
   * Find VMService by Id
   * 
   * @param id the PK of VMService
   */
  VMService findById( int id );

  /**
   * Find all VMServices
   */
  List<VMService> findAllVMServices();

  /**
   * Save or update VMService
   */
  VMService saveAndUpdate( VMService vmService );

  /**
   * delete VMService,
   * 
   * @return true: the delete succeed. 
   *         false: the removal failed. it may be cause by VMService are
   *         used by customer or VMService which want delete is null
   */
  boolean deleteVMService( VMService vmService );

}
