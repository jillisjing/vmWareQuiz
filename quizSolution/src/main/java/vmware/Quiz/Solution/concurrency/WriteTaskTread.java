package vmware.Quiz.Solution.concurrency;

import java.util.ArrayList;
import java.util.List;

public class WriteTaskTread extends Thread
{
  private SafeReadAndWriteWithLock _readAndWrite;
  public static volatile int _writeTimes = 10;
  public List<String> _resultList = new ArrayList<>();

  public WriteTaskTread(SafeReadAndWriteWithLock readAndWrite) {
      this._readAndWrite = readAndWrite;
  }

  public void run()
  {
    synchronized ( this ) {
      while ( _writeTimes > 0 ) {
        try {
          
          String doc = "book " + Thread.currentThread().getName() + _writeTimes;
          _writeTimes--;
          _readAndWrite.write( doc );
          _resultList.add( doc );
         
        }
        catch ( InterruptedException e1 ) {
          System.err.println( "write a doc error: " + e1.getMessage() );
        }
      }
    }

    try {
      Thread.sleep( 20 );
    }
    catch ( InterruptedException e ) {
      e.printStackTrace();
    }
  }
}
