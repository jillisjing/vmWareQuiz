package vmware.Quiz.Solution.collection;

import java.util.ArrayList;
import java.util.List;
/*
 * The Utils for merge two sorted collections. The object which in list for merge
 * should extends {@link CompareBase}
 * 
 */
public class MergeCollectionsUtil< T extends CompareBase<T> >
{
  /**
   * Merge two sorted collections of the same kind into a single sorted collection. And the object which need sort
   * should extends {@link CompareBase}
   * 
   * @param list1 is sorted list
   * @param list2 is sorted list
   */
  public List<T> mergeTwoSortedList( List<T> list1, List<T> list2 )
  {
    if ( list1 == null && list2 == null )
      return null;

    if ( list1 == null )
      return list2;

    if ( list2 == null )
      return list1;

    List<T> mergedList = new ArrayList<>();
    int i = 0;
    int j = 0;
    int length1 = list1.size();
    int length2 = list2.size();

    while ( length1 > i && length2 > j )
    {
      if ( list1.get( i ).compareTo( list2.get( j ) ) > 0 )
      {
        mergedList.add( list2.get( j++ ) );
      }
      else
      {
        mergedList.add( list1.get( i++ ) );
      }
    }

    while ( length1 > i )
    {
      mergedList.add( list1.get( i++ ) );
    }
    while ( length2 > j )
    {
      mergedList.add( list2.get( j++ ) );
    }
    return mergedList;
  }

}
