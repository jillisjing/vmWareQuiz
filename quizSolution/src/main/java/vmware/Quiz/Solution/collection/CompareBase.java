package vmware.Quiz.Solution.collection;

public abstract class CompareBase<T> implements Comparable<T>
{

  @Override
  public int compareTo( T o )
  {
    return 0;
  }

  @Override
  public String toString()
  {
    return "CompareBase []";
  }

}
