package vmware.Quiz.Solution.collection;

public class CompareObject extends CompareBase<CompareObject>
{
  private int _age;

  public CompareObject( int age )
  {
    _age = age;
  }

  @Override
  public int compareTo( CompareObject anotherCO )
  {
    return compare( this._age, anotherCO._age );
  }

  public static int compare( int x, int y )
  {
    return ( x < y ) ? -1 : ( ( x == y ) ? 0 : 1 );
  }

  public int get_age()
  {
    return _age;
  }

  @Override
  public String toString()
  {
    return "[_age=" + _age + "]";
  }

}
