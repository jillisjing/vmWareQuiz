package vmware.Quiz.Solution.unit;


import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.junit.Assert;
import org.junit.Test;

import vmware.Quiz.Solution.concurrency.ReadTaskCallable;
import vmware.Quiz.Solution.concurrency.ReadTaskThread;
import vmware.Quiz.Solution.concurrency.SafeReadAndWriteUtil;
import vmware.Quiz.Solution.concurrency.WriteTaskThread;

public class SafeReadAndWriteUtilTest
{
  
  @Test
  public void testTwoWriterAndRead() throws InterruptedException
  {
    SafeReadAndWriteUtil<String> readAndWrite = new SafeReadAndWriteUtil<>( 10 );
    int docNum = 1000;
    ReadTaskThread._readTimes = docNum;
    WriteTaskThread._writeTimes = docNum;
    ReadTaskThread read1 = new ReadTaskThread( readAndWrite );
    read1.start();
    ReadTaskThread read2 = new ReadTaskThread( readAndWrite );
    read2.start();
    
    WriteTaskThread write1 = new WriteTaskThread( readAndWrite );
    write1.start();
    WriteTaskThread write2 = new WriteTaskThread( readAndWrite );
    write2.start();

    // wait all of sub-thread finished
    while ( read1.isAlive() )
    {
    }
    while ( write1.isAlive() )
    {
    }
    while ( write2.isAlive() )
    {
    }

    int read1Size = read1._resultList.size();
    int read2Size = read2._resultList.size();
    int write1Size = write1._resultList.size();
    int write2Size = write2._resultList.size();
    Set<String> writeResult = mergeListAndConvertToSet( write1._resultList, write1Size, write2._resultList,
                                                        write2Size );
    Assert.assertEquals( docNum, writeResult.size() );
    Set<String> readResult = mergeListAndConvertToSet( read1._resultList, read1Size, read2._resultList, read2Size );
    Assert.assertEquals( docNum, readResult.size() );

    for ( String wr : writeResult )
    {
      Assert.assertTrue( readResult.contains( wr ) );
    }
  }
  
  @Test
  public void testWriteMoreThanRead() throws InterruptedException
  {
    SafeReadAndWriteUtil<String> readAndWrite = new SafeReadAndWriteUtil<>( 10 );
    ReadTaskThread._readTimes = 4;
    WriteTaskThread._writeTimes = 10;
    ReadTaskThread read1 = new ReadTaskThread( readAndWrite );
    read1.start();

    WriteTaskThread write1 = new WriteTaskThread( readAndWrite );
    write1.start();
    WriteTaskThread write2 = new WriteTaskThread( readAndWrite );
    write2.start();
    while ( read1.isAlive() )
    {
    }
    while ( write1.isAlive() )
    {
    }
    while ( write2.isAlive() )
    {
    }

    int read1Size = read1._resultList.size();
    int write1Size = write1._resultList.size();
    int write2Size = write2._resultList.size();

    Set<String> writeResult = mergeListAndConvertToSet( write1._resultList, write1Size, write2._resultList,
                                                        write2Size );
    Assert.assertEquals( 10, write1Size + write2Size );

    Set<String> readResult = mergeListAndConvertToSet( read1._resultList, read1Size );
    Assert.assertEquals( 4, readResult.size() );

    for ( String rr : readResult )
    {
      Assert.assertTrue( writeResult.contains( rr ) );
    }
  }
  
  @Test
  public void testReadMoreThanWrite() throws InterruptedException
  {
    SafeReadAndWriteUtil<String> readAndWrite = new SafeReadAndWriteUtil<>( 10 );
    ReadTaskThread._readTimes = 8;
    WriteTaskThread._writeTimes = 3;
    // only 3 docs can read, so avoid endless waiting read doc, 
    // so read thread just waiting 5 seconds, if still no doc to read,read thread will interrupted.
    ExecutorService executor = Executors.newCachedThreadPool();
    ReadTaskCallable read1 = new ReadTaskCallable( readAndWrite );
    Future<Boolean> f1 = executor.submit( read1 );

    WriteTaskThread write1 = new WriteTaskThread( readAndWrite );
    write1.start();
    WriteTaskThread write2 = new WriteTaskThread( readAndWrite );
    write2.start();

    while ( write1.isAlive() )
    {
    }
    while ( write2.isAlive() )
    {
    }

    try
    {
      // only 3 documents for read, avoid endless wait, it will interrupted after 5 seconds
      if ( f1.get( 5, TimeUnit.SECONDS ) )
      {
        System.out.println( "one complete successfully" );
      }
    }
    catch ( InterruptedException | ExecutionException e )
    {
      executor.shutdownNow();
    }
    catch ( TimeoutException e )
    {
      f1.cancel( true );
    }
    finally
    {
      executor.shutdownNow();
    }

    int read1Size = read1._resultList.size();
    int write1Size = write1._resultList.size();
    int write2Size = write2._resultList.size();

    Set<String> writeResult = mergeListAndConvertToSet( write1._resultList, write1Size, write2._resultList,
                                                        write2Size );
    Assert.assertEquals( 3, write1Size + write2Size );

    Set<String> readResult = mergeListAndConvertToSet( read1._resultList, read1Size );
    Assert.assertEquals( 3, readResult.size() );

    for ( String rr : readResult )
    {
      Assert.assertTrue( writeResult.contains( rr ) );
    }
  }
  
  private Set<String> mergeListAndConvertToSet( List<String> list1, int l1Size )
  {
    return mergeListAndConvertToSet( list1,l1Size, Collections.emptyList(), 0 );
  }
  
  private Set<String> mergeListAndConvertToSet( List<String> list1, int l1Size, List<String> list2, int l2size )
  {
    Set<String> resultSet = new HashSet<>();

    int l = l1Size > l2size ? list1.size() : l2size;

    for ( int i = 0; i < l; i++ )
    {
      if ( i < l1Size )
      {
        resultSet.add( list1.get( i ) );
      }
      if ( i < list2.size() )
      {
        resultSet.add( list2.get( i ) );
      }
    }
    return resultSet;
  }

}
