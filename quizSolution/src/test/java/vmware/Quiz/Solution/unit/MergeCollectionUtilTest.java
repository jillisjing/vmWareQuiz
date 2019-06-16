package vmware.Quiz.Solution.unit;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import vmware.Quiz.Solution.collection.CompareObject;
import vmware.Quiz.Solution.collection.MergeCollectionsUtil;


public class MergeCollectionUtilTest
{
  private List<CompareObject> expectList;
  private List<CompareObject> List1 = null;
  private List<CompareObject> List2 = null;

  @Test
  public void testMergeTwoSortedListWithEmptyList()
  {
    MergeCollectionsUtil<CompareObject> ms = new MergeCollectionsUtil<>();
    List<CompareObject> mergeList = ms.mergeTwoSortedList( List1, List2 );
    Assert.assertNull( mergeList );
  }

  @Test
  public void testMergeTwoSortedListList1IsNull()
  {
    List2 = new ArrayList<>();
    List2.add( new CompareObject( 1 ) );
    List2.add( new CompareObject( 2 ) );
    List2.add( new CompareObject( 5 ) );
    List2.add( new CompareObject( 12 ) );
    List2.add( new CompareObject( 15 ) );
    List2.add( new CompareObject( 20 ) );
    List2.add( new CompareObject( 21 ) );
    MergeCollectionsUtil<CompareObject> ms = new MergeCollectionsUtil<>();
    List<CompareObject> mergeList = ms.mergeTwoSortedList( List1, List2 );
    Assert.assertEquals( List2.toString(), mergeList.toString() );
  }

  @Test
  public void testMergeTwoSortedListList2IsNull()
  {
    List1 = new ArrayList<>();
    List1.add( new CompareObject( 3 ) );
    List1.add( new CompareObject( 6 ) );
    List1.add( new CompareObject( 7 ) );
    List1.add( new CompareObject( 10 ) );
    List1.add( new CompareObject( 13 ) );
    MergeCollectionsUtil<CompareObject> ms = new MergeCollectionsUtil<>();
    List<CompareObject> mergeList = ms.mergeTwoSortedList( List1, List2 );
    Assert.assertEquals( List1.toString(), mergeList.toString() );
  }

  @Test
  public void testMergeTwoSortedList()
  {
    expectList = new ArrayList<>();
    expectList.add( new CompareObject( 1 ) );
    expectList.add( new CompareObject( 2 ) );
    expectList.add( new CompareObject( 3 ) );
    expectList.add( new CompareObject( 5 ) );
    expectList.add( new CompareObject( 6 ) );
    expectList.add( new CompareObject( 7 ) );
    expectList.add( new CompareObject( 10 ) );
    expectList.add( new CompareObject( 12 ) );
    expectList.add( new CompareObject( 13 ) );
    expectList.add( new CompareObject( 15 ) );
    expectList.add( new CompareObject( 20 ) );
    expectList.add( new CompareObject( 21 ) );

    List1 = new ArrayList<>();
    List1.add( new CompareObject( 3 ) );
    List1.add( new CompareObject( 6 ) );
    List1.add( new CompareObject( 7 ) );
    List1.add( new CompareObject( 10 ) );
    List1.add( new CompareObject( 13 ) );
    List2 = new ArrayList<>();
    List2.add( new CompareObject( 1 ) );
    List2.add( new CompareObject( 2 ) );
    List2.add( new CompareObject( 5 ) );
    List2.add( new CompareObject( 12 ) );
    List2.add( new CompareObject( 15 ) );
    List2.add( new CompareObject( 20 ) );
    List2.add( new CompareObject( 21 ) );
    MergeCollectionsUtil<CompareObject> ms = new MergeCollectionsUtil<>();
    List<CompareObject> mergeList = ms.mergeTwoSortedList( List1, List2 );

    Assert.assertEquals( expectList.toString(), mergeList.toString() );
  }

}
