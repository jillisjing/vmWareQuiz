package vmware.Quiz.Solution.DBProject.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import vmware.Quiz.Solution.DBProject.bean.VMService;

public interface VMServiceRepository extends JpaRepository<VMService, Integer>
{
}
