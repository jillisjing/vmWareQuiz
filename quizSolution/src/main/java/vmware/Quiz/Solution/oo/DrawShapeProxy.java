package vmware.Quiz.Solution.oo;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class DrawShapeProxy implements InvocationHandler
{
  private Object target;

  public Object bind( Object target )
  {
    this.target = target;
    return Proxy.newProxyInstance( target.getClass().getClassLoader(), target.getClass().getInterfaces(), this );
  }

  @Override
  public Object invoke( Object proxy, Method method, Object[] args ) throws Throwable
  {
    Object result = null;
    System.out.println( "invoke some preprocessing before drawing——————" );
    result = method.invoke( target, args );
    System.out.println( "invoke some preprocessing after drawing——————" );
    return result;
  }

}
