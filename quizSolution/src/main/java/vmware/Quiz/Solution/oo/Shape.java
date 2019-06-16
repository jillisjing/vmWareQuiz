package vmware.Quiz.Solution.oo;

public class Shape
{
  public String name;
  public String color;

  public String getName()
  {
    return name;
  }

  public void setName( String name )
  {
    this.name = name;
  }

  public String getColor()
  {
    return color;
  }

  public void setColor( String color )
  {
    this.color = color;
  }

  @Override
  public String toString()
  {
    return "Shape [name=" + name + ", color=" + color + "]";
  }

}
