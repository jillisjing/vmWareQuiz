package vmware.Quiz.Solution.DBProject.bean;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "VM_SERVICE")
public class VMService
{
  @Id
  @GeneratedValue
  private Integer id;
  
  @NotNull
  @Column(length = 20)
  private String serName;
  
  @NotNull
  @Temporal(TemporalType.TIMESTAMP)
  @Column
  private Date createDate;
  

  @ManyToMany( fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE } )
  @JoinTable( name = "service_customer_mapping", 
              joinColumns = { @JoinColumn( name = "service_id", referencedColumnName = "id" ) }, 
                              inverseJoinColumns = { @JoinColumn( name = "customer_id", referencedColumnName = "id" ) } )
  private Set<Customer> customers;

  public Integer getId()
  {
    return id;
  }

  public void setId( Integer id )
  {
    this.id = id;
  }

  public String getSerName()
  {
    return serName;
  }

  public void setSerName( String serName )
  {
    this.serName = serName;
  }

  public Date getCreateDate()
  {
    return createDate;
  }

  public void setCreateDate( Date createDate )
  {
    this.createDate = createDate;
  }

  public Set<Customer> getCustomers()
  {
    return customers;
  }

  public void setCustomers( Set<Customer> customers )
  {
    this.customers = customers;
  }
  
}
