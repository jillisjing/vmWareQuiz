package vmware.Quiz.Solution.unit;


import org.junit.Test;

import vmware.Quiz.Solution.oo.Circle;
import vmware.Quiz.Solution.oo.DrawShapeFactory;
import vmware.Quiz.Solution.oo.DrawShapeFactoryImpl;
import vmware.Quiz.Solution.oo.DrawShapeProxy;
import vmware.Quiz.Solution.oo.Triangle;


public class DrawShapeProxyTest
{
  @Test
  public void testDrawCircle()
  {
    DrawShapeProxy proxy = new DrawShapeProxy();
    DrawShapeFactory factory = (DrawShapeFactory) proxy.bind(new DrawShapeFactoryImpl()); 
    Circle shape = new Circle();
    shape.setColor( "blue" );
    shape.setName( "circle" );
    shape.setDiameter( 20.5f );
    shape.setArea( 50 );
    factory.draw( shape );
  }
  
  @Test
  public void testTriangle()
  {
    DrawShapeProxy proxy = new DrawShapeProxy();
    DrawShapeFactory factory = (DrawShapeFactory) proxy.bind(new DrawShapeFactoryImpl()); 
    Triangle triangle = new Triangle();
    triangle.setColor( "blue" );
    triangle.setName( "circle" );
    triangle.setEquilateral(false);
    triangle.setBottomLength( 10.2f );
    triangle.setLeftLength( 15.2f );
    triangle.setRightLength(  18.2f );

    factory.draw( triangle );
  }
}
