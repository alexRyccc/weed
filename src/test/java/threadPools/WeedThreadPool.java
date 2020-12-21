package threadPools;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

/**
 * @author: create by Alexryc
 * @version: v1.0
 * @description: threadPools
 * @date 2020/12/18 10:50
 */
public class WeedThreadPool extends ThreadPoolExecutor {
    private final ThreadLocal<Long> startTime =new ThreadLocal<>();
    private final Logger log =Logger.getLogger("WeedThreadPool");
    //统计执行次数
    private final AtomicLong numTasks =new AtomicLong();
    //统计总执行时间
    private final AtomicLong totalTime =new AtomicLong();
    /**
     * 这里是实现线程池的构造方法，我随便选了一个，大家可以根据自己的需求找到合适的构造方法
     */
    public WeedThreadPool(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
    }
    //线程执行之前调用
    protected void  beforeExecute(Thread t,Runnable r){
        super.beforeExecute(t,r);
        System.out.println(String.format("Thread %s:start %s",t,r));
        //因为currentTimeMillis返回的是ms，而众所周知ms是很难产生差异的，所以换成了nanoTime用ns来展示
        startTime.set(System.nanoTime());
    }
    //线程执行之后调用
    protected void afterExecute(Runnable r,Throwable t){
        try {
            Long endTime =System.nanoTime();
            Long taskTime =endTime-startTime.get();
            numTasks.incrementAndGet();
            totalTime.addAndGet(taskTime);
            System.out.println(String.format("Thread %s:end %s, time=%dns",Thread.currentThread(),r,taskTime));
        }finally {
            super.afterExecute(r,t);
        }
    }
    //线程池退出时候调用
   protected void terminated(){
        try{
            System.out.println(String.format("Terminated: avg time =%dns, ",totalTime.get()/numTasks.get()));
        }finally {
            super.terminated();
        }
   }

}
