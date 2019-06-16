package vmware.Quiz.Solution.DBProject.bean;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;


@Entity
@Table(name = "VM_CUSTOMER")
public class Customer
{
  @Id
  @Column(name="id")
  @GeneratedValue
  private Integer id;
  
  @NotNull
  @Column(length = 20)
  private String customerName;
  
  @NotNull
  @Column
  private Integer age;

  @ManyToMany( fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE }, mappedBy = "customers" )
  private Set<VMService> services = new HashSet<>();

  public Integer getId()
  {
    return id;
  }

  public void setId( Integer id )
  {
    this.id = id;
  }

  public String getCustomerName()
  {
    return customerName;
  }

  public void setCustomerName( String customerName )
  {
    this.customerName = customerName;
  }

  public Integer getAge()
  {
    return age;
  }

  public void setAge( Integer age )
  {
    this.age = age;
  }

  public Set<VMService> getServices()
  {
    return services;
  }

  public void setServices( Set<VMService> services )
  {
    this.services = services;
  }
  
}
