package vmware.Quiz.Solution.DBProject.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import vmware.Quiz.Solution.DBProject.bean.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer>
{

}
