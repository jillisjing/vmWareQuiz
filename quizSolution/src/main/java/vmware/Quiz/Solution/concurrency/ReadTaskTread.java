package vmware.Quiz.Solution.concurrency;

import java.util.ArrayList;
import java.util.List;

public class ReadTaskTread extends Thread
{
  private SafeReadAndWriteWithLock _readAndWrite;
  public static volatile int _readTimes = 10;

  public List<String> _resultList = new ArrayList<>();
  public ReadTaskTread( SafeReadAndWriteWithLock readAndWrite )
  {
    this._readAndWrite = readAndWrite;
  }

  public void run()
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
          return; 
        }
      }
    }
    try {
      Thread.sleep( 10 );
    }
    catch ( InterruptedException e ) {
      e.printStackTrace();
      System.err.println( "read a doc error: " +  e.getMessage() );
      return; 
    }
  }
}
