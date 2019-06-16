package vmware.Quiz.Solution.oo;

public class DrawShapeFactoryImpl implements DrawShapeFactory 
{

  @Override
  public void draw( Shape shape )
  {
    System.out.println( "drawing a shape:" + shape.toString() );
  }

}
