package vmware.Quiz.Solution.oo;

public class Triangle extends Shape
{
  private boolean isEquilateral;

  private float leftLength;
  private float rightLength;
  private float bottomLength;

  public boolean isEquilateral()
  {
    return isEquilateral;
  }

  public void setEquilateral( boolean isEquilateral )
  {
    this.isEquilateral = isEquilateral;
  }

  public float getLeftLength()
  {
    return leftLength;
  }

  public void setLeftLength( float leftLength )
  {
    this.leftLength = leftLength;
  }

  public float getRightLength()
  {
    return rightLength;
  }

  public void setRightLength( float rightLength )
  {
    this.rightLength = rightLength;
  }

  public float getBottomLength()
  {
    return bottomLength;
  }

  public void setBottomLength( float bottomLength )
  {
    this.bottomLength = bottomLength;
  }

  @Override
  public String toString()
  {
    return "Triangle [name=" + name + ", color=" + color + "isEquilateral=" + isEquilateral + ", leftLength="
           + leftLength + ", rightLength=" + rightLength + ", bottomLength=" + bottomLength + "]";
  }

}
