package vmware.Quiz.Solution.DBProject.service.impl;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vmware.Quiz.Solution.DBProject.bean.Customer;
import vmware.Quiz.Solution.DBProject.repository.CustomerRepository;
import vmware.Quiz.Solution.DBProject.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService
{
  @Autowired
  CustomerRepository repository;

  @Override
  public Customer findById( int id )
  {
    Customer ct = null;
    try
    {
      ct = repository.findById( id ).get();
    }
    catch ( NoSuchElementException e )
    {
      //ignore
    }
    return ct;
  }

  @Override
  public List<Customer> findAllCustomers()
  {
    return repository.findAll();
  }

  @Override
  public Customer saveAndUpdate( Customer customer )
  {
    if ( customer == null )
    {
      return null;
    }
    return repository.saveAndFlush( customer );
  }
  
  public boolean deleteCustomer( Customer customer )
  {
    if ( customer == null || customer.getId() == null )
    {
      return false;
    }
    repository.delete( customer );
    return true;
  }
}
