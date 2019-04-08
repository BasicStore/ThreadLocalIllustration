package app;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

// NOTE: the idea is that any system job will inherit this class
public class ThreadLocalMain implements Callable {
    
	// Atomic integer containing the next thread ID to be assigned
    private static AtomicInteger nextThreadId = new AtomicInteger(0);
    
    // Thread local variable containing each thread's ID
    private static ThreadLocal<Integer> threadId =
       ThreadLocal.withInitial(() -> nextThreadId.getAndIncrement());
    
    
    
    // TODO this is not thread safe...........
	public ThreadLocalMain() {

	}
	
    
    // Returns the current thread's unique ID, assigning it if necessary
    // AS DISTINCT FROM THE TASKS OR NUMBER OF THREAD REQUESTS
    // ie it is the thread id (0, 1, 2) not the java instance of a thread, which would be (0, 1, 2, 3, 4)
    public static Integer getThreadId(){
      return threadId.get();
    }
 
    @Override
    public String call() {
    	try {
        	Thread.sleep(1000);
            System.out.println("My Thread ID  "+getThreadId());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    	
    	return "taskId is complete";
    	
    }
 
//    public static void main(String[] args) {
//      final ExecutorService executorService = Executors.newFixedThreadPool(3);
//      final ThreadLocalMain threadLocalExample = new ThreadLocalMain();
//      // since there are 3 threads in the created thread pool,thread id range from 0-2
//      IntStream.range(0,8).forEach(i -> executorService.submit(threadLocalExample));
//    }
   
    
}
 
