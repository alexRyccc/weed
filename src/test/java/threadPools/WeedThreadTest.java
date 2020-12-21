package threadPools;

import java.util.Random;
import java.util.concurrent.*;

/**
 * @author: create by Alexryc
 * @version: v1.0
 * @description: threadPools
 * @date 2020/12/18 11:06
 */
public class WeedThreadTest {



     BlockingQueue<Runnable> taskQueue;
   final static WeedThreadPool weedThreadPool =new WeedThreadPool(3,10,1, TimeUnit.SECONDS,new ArrayBlockingQueue<Runnable>(100));
    public static void main(String[] args) throws InterruptedException {
        for(int i=0;i<3;i++) {
            weedThreadPool.execute(WeedThreadTest::run);
        }
        Thread.sleep(2000L);
        weedThreadPool.shutdown();
    }

    private static void run() {
        System.out.println("thread id is: " + Thread.currentThread().getId());
        try {

            Thread.sleep(1024L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }



}
