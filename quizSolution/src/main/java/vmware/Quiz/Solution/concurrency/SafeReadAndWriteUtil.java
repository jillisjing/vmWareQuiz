package vmware.Quiz.Solution.concurrency;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class SafeReadAndWriteUtil<T>
{
  static class Node<T> {
    T item;
    Node<T> next;
    Node(T x) { item = x; }
}
  transient Node<T> head;

  private transient Node<T> last;
  
  private final int _capacity;
  private final AtomicInteger count = new AtomicInteger();
  
  private final ReentrantLock readLock = new ReentrantLock();
  private Condition canRead = readLock.newCondition();
  private final ReentrantLock writeLock = new ReentrantLock();
  private Condition canWrite = writeLock.newCondition();
  
  public SafeReadAndWriteUtil(int capacity) {
    this._capacity = capacity;
    last = head = new Node<>(null);
  }
  
  /**
   * write doc with lock
   * 
   * @throws InterruptedException
   */
  public void write(T doc) throws InterruptedException {
    if ( doc == null ) throw new NullPointerException();
    int c = -1;
    Node<T> node = new Node<>(doc);
    final ReentrantLock writelock = this.writeLock;
    final AtomicInteger count = this.count;
    writelock.lockInterruptibly();
    try {
      while ( count.get() == _capacity ) {
        System.out.println( "warning：Thread(" + Thread.currentThread().getName()
                            + ") want to write doc，but the queue is full." );
        canWrite.await();
      }
      last = last.next = node;
      c = count.getAndIncrement();
      System.out.println( "Thread (" + Thread.currentThread().getName() + ") writed doc: " + doc + ";");
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
   * read doc with lock.
   */
  public T read() throws InterruptedException
  {
    T doc = null;
    int c = -1;
    final AtomicInteger count = this.count;
    final ReentrantLock readlock = this.readLock;
    readlock.lockInterruptibly();
    try
    {
      while ( count.get() == 0 )
      {
        System.out.println( "warning：Thread(" + Thread.currentThread().getName()
                            + ") want to read doc，But there are no products" );
        canRead.await();
      }
      Node<T> h = head;
      Node<T> first = h.next;
      h.next = h; // help GC
      head = first;
      doc = first.item;
      first.item = null;
      c = count.getAndDecrement();
      System.out.println( "Thread(" + Thread.currentThread().getName() + ") read a doc:" + doc + ";" );
      if ( c > 1 )
        canRead.signal();
    }
    finally
    {
      readlock.unlock();
    }
    if ( c == _capacity )
    {
      final ReentrantLock writeLock = this.writeLock;
      writeLock.lock();
      try
      {
        canWrite.signal();
      }
      finally
      {
        writeLock.unlock();
      }
    }
    return doc;
  } 
}
