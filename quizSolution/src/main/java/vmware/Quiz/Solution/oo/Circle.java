package vmware.Quiz.Solution.oo;

public class Circle extends Shape
{
  private float diameter;
  private float area;

  public float getDiameter()
  {
    return diameter;
  }

  public void setDiameter( float diameter )
  {
    this.diameter = diameter;
  }

  public float getArea()
  {
    return area;
  }

  public void setArea( float area )
  {
    this.area = area;
  }

  @Override
  public String toString()
  {
    return "Circle [name=" + name + ", color=" + color + ", diameter=" + diameter + ", area=" + area + "]";
  }

}
