package vmware.Quiz.Solution.concurrency;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

public class ReadTaskCallable implements Callable<Boolean>
{
  private SafeReadAndWriteWithLock _readAndWrite;
  public static volatile int _readTimes = 10;

  public List<String> _resultList = new ArrayList<>();
  public ReadTaskCallable( SafeReadAndWriteWithLock readAndWrite )
  {
    this._readAndWrite = readAndWrite;
  }
  
  @Override
  public Boolean call() throws Exception
  {
    synchronized ( this ) {
      while ( _readTimes > 0 ) {
        try {
          _readTimes--;
          String _result = _readAndWrite.read();
          _resultList.add( _result );
        }
        catch ( InterruptedException e1 ) {
          System.err.println( "read a doc error: " + e1.getMessage() );
          return false; 
        }
      }
    }
    try {
      Thread.sleep( 10 );
    }
    catch ( InterruptedException e ) {
      System.err.println( "read a doc failed: " +  e.getMessage() );
      return false;  
    }
    return true;
  }
}
