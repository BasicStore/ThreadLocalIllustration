package app;

import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

//private static final ThreadLocal<SimpledateFormat> df= ThreadLocal.withInitial(()-> new SDF("yyyy-MM-dd"));

//https://howtodoinjava.com/java/multi-threading/when-and-how-to-use-thread-local-variables/

public class Example2 implements Runnable
{
	
    // Atomic integer containing the next thread ID to be assigned
	   private static final AtomicInteger nextId   = new AtomicInteger(0);
	    
	   // Thread local variable containing each thread's ID
//	   private static final ThreadLocal<Integer> threadId = new ThreadLocal<Integer>()
//	                                                         {
//	                                                            @Override
//	                                                            protected Integer initialValue()
//	                                                            {
//	                                                               return nextId.getAndIncrement();
//	                                                            }
//	                                                         };
	   private static final ThreadLocal<Integer> threadId = ThreadLocal.withInitial(()-> nextId.getAndIncrement());	                                                         
	                                                         
	                                                         
	                                                         
	                                                         
	   // Returns the current thread's unique ID, assigning it if necessary
	   public int getThreadId()
	   {
	      return threadId.get();
	   }
	   
	   
	   private static final ThreadLocal<Date> startDate = ThreadLocal.withInitial(()-> new Date());
	   
	 
	   @Override
	   public void run()
	   {
	      System.out.printf("Starting Thread: %s : %s\n", getThreadId(), startDate.get());
	      try
	      {
	         TimeUnit.SECONDS.sleep((int) Math.rint(Math.random() * 10));
	      } catch (InterruptedException e)
	      {
	         e.printStackTrace();
	      }
	      System.out.printf("Thread Finished: %s : %s\n", getThreadId(), startDate.get());
	   }
	
	   
// not 100% successful.......	
//	   public static void main(String[] args) {
//	      final ExecutorService executorService = Executors.newFixedThreadPool(3);
//	      final Example2 threadLocalExample = new Example2();
//	      // since there are 3 threads in the created thread pool,thread id range from 0-2
//	      IntStream.range(0,5).forEach(i -> executorService.submit(threadLocalExample));
//	    }
//	
	
}