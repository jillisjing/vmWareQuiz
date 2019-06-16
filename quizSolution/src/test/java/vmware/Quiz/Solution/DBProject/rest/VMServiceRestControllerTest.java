package vmware.Quiz.Solution.DBProject.rest;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Date;
import java.util.List;

import org.assertj.core.util.Lists;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import vmware.Quiz.Solution.DBProject.bean.VMService;
import vmware.Quiz.Solution.DBProject.service.VMServiceService;

@RunWith( SpringRunner.class )
@SpringBootTest( webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT )
public class VMServiceRestControllerTest
{
  @Autowired
  private TestRestTemplate restTemplate;

  @Before
  public void setUp() throws Exception
  {
    VMServiceService vMServiceService = mock( VMServiceService.class );
    List<VMService> cts = Lists.newArrayList();
    when( vMServiceService.findAllVMServices() ).thenReturn( cts );
  }

  @Test
  @SuppressWarnings( "unchecked" )
  public void testGetVMServices() throws Exception
  {
    List<VMService> response = restTemplate.getForObject( "/vmware/vmservice/getAll", List.class );
    Assert.assertNotNull( response );
  }

  @Test
  @SuppressWarnings( "unchecked" )
  public void testCreateOrUpdateVMServices() throws Exception
  {

    List<VMService> response1 = restTemplate.getForObject( "/vmware/vmservice/getAll", List.class );

    VMService vMService = new VMService();
    vMService.setSerName( "test" );
    vMService.setCreateDate( new Date() );
    VMService resVMService = restTemplate.postForObject( "/vmware/vmservice/createOrUpdate", vMService,
                                                         VMService.class );
    Assert.assertEquals( resVMService.getSerName(), "test" );

    List<VMService> response2 = restTemplate.getForObject( "/vmware/vmservice/getAll", List.class );
    Assert.assertEquals( response1.size() + 1, response2.size() );

    restTemplate.postForObject( "/vmware/vmservice/delete", resVMService, String.class );

    List<VMService> response3 = restTemplate.getForObject( "/vmware/vmservice/getAll", List.class );
    Assert.assertEquals( response1.size() + 1, response3.size() );
  }

}
