package threadPools;

import java.util.Objects;
import java.util.Random;
import java.util.concurrent.*;

/**
 * @author: create by Alexryc
 * @version: v1.0
 * @description: threadPools
 * @date 2020/12/18 16:41
 */

public class WeedExecutorServiceDemo {
    /**
     * 继续用之前建好的线程池，只是调整一下池大小
     */
    BlockingQueue<Runnable> taskQueue;
    final static WeedThreadPool weedThreadPool = new WeedThreadPool(1, 5, 1, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(100));
    public static Random r = new Random();

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        Thread.sleep(1000L);
        CompletionService<Integer> cs = new ExecutorCompletionService<Integer>(weedThreadPool);
        for (int i = 0; i < 3; i++) {
            cs.submit(() -> {
                //获取计算任务
                int init = 0;
                for (int j = 0; j < 100; j++) {
                    init += r.nextInt();
                }
                Thread.sleep(1000);
                return Integer.valueOf(init);
            });
        }
        weedThreadPool.shutdown();
        /**
         * 通过poll方法获取
         */
        for (int i = 0; i < 3; i++) {
            Future<Integer> poll = cs.poll(800L, TimeUnit.MILLISECONDS);
            if (poll==null){
                System.out.println("执行结束");
                weedThreadPool.shutdownNow();
            }

        }
    }
}
