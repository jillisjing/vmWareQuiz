package vmware.Quiz.Solution.concurrency;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class SafeReadAndWriteWithLock
{
  private final List<String> documents;
  private final int _capacity;
  private final AtomicInteger count = new AtomicInteger();
  
  private final ReentrantLock readLock = new ReentrantLock();
  private Condition canRead = readLock.newCondition();
  private final ReentrantLock writeLock = new ReentrantLock();
  private Condition canWrite = writeLock.newCondition();
  
  public SafeReadAndWriteWithLock(int capacity) {
    this._capacity = capacity;
    this.documents = new ArrayList<>( _capacity );
  }
  
  /**
   * write doc with lock, if documents full more than 2 seconds, will stop wait
   * 
   * @throws InterruptedException
   */
  public void write(String doc) throws InterruptedException {
    if ( doc == null ) throw new NullPointerException();
    int c = -1;
    final ReentrantLock writelock = this.writeLock;
    final AtomicInteger count = this.count;
    writelock.lockInterruptibly();
    try {
      while ( count.get() == _capacity ) {
        System.out.println( "warning：Thread(" + Thread.currentThread().getName()
                            + ") want to write doc，but the queue is full." );
        canWrite.await();
      }
      documents.add( doc );
      c = count.getAndIncrement();
      System.out.println( "Thread (" + Thread.currentThread().getName() + ") writed doc: " + doc + "; number of docs: "
                          + documents.size() );
      if (c + 1 < _capacity) 
          canWrite.signal();
    } finally {
      writelock.unlock();
    }
    if ( c == 0 ) {
      final ReentrantLock readLock = this.readLock;
      readLock.lock();
      try {
        canRead.signal();
      }
      finally {
        readLock.unlock();
      }
    }
  }
  
  /**
   * read doc with lock, if documents empty more than 2 seconds, will stop wait
   * @throws InterruptedException 
   */
  public String read() throws InterruptedException
  {
    String  doc = null;
    int c = -1;
    final AtomicInteger count = this.count;
    final ReentrantLock readlock = this.readLock;
    readlock.lock();
    try {
      while ( count.get() == 0 ) {
        System.out.println( "warning：Thread(" + Thread.currentThread().getName()
                            + ") want to read doc，But there are no products" );
        canRead.await();
      }
      doc = documents.remove( 0 );
      c = count.getAndDecrement();
      System.out.println( "Thread(" + Thread.currentThread().getName() + ") read a doc:" + doc + "; number of docs:"
                          + documents.size() );
      if ( c > 1 )
        canRead.signal();
    }
    finally {
      readlock.unlock();
    }
    if ( c == _capacity ){
      final ReentrantLock writeLock = this.writeLock;
      writeLock.lock();
      try {
          canWrite.signal();
      } finally {
        writeLock.unlock();
      }
    }
    return doc;
  } 
}
