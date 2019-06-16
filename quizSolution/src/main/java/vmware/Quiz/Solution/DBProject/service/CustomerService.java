package vmware.Quiz.Solution.DBProject.service;

import java.util.List;

import vmware.Quiz.Solution.DBProject.bean.Customer;

public interface CustomerService
{
  /**
   * Find customer by Id
   * 
   * @param id the PK of customer
   */
  Customer findById( int id );

  /**
   * Find all customers
   */
  List<Customer> findAllCustomers();

  /**
   * Save or update customer
   */
  Customer saveAndUpdate( Customer customer );

  /**
   * delete customer
   * @return true: the delete succeed. 
   *         false: the removal failed because customer which want to delete is null
   */
  boolean deleteCustomer( Customer customer );
}
