package threadPools;

import org.junit.Test;

import java.util.Random;
import java.util.concurrent.*;

/**
 * @author: create by Alexryc
 * @version: v1.0
 * @description: threadPools
 * @date 2020/12/18 15:13
 */
public class CompletionServiceDemo {
    @Test
    public void test() throws InterruptedException, ExecutionException {
        Executor executor = Executors.newFixedThreadPool(3);
        CompletionService<String> service = new ExecutorCompletionService<>(executor);
        for (int i = 0 ; i < 5 ;i++) {
            int seqNo = i;

            service.submit(new Callable<String>() {

                @Override
                public String call() throws Exception {
                    Thread.sleep(2000);
                    return "HelloWorld-" + seqNo + "-" + Thread.currentThread().getName();
                }
            });
        }
        for (int j = 0 ; j < 5; j++) {
            System.out.println(service.take().get());
        }
    }

    public static void main(String[] args) throws Exception{

        Executor executor = Executors.newFixedThreadPool(3);
        CompletionService<Integer> cs = new ExecutorCompletionService<Integer>(executor);
        //List<Future<Integer>> result = new ArrayList<Future<Integer>>(10);
        for(int i=0; i< 10; i++){
            cs.submit(new Callable<Integer>() {
                @Override
                public Integer call() throws Exception {
                    Random r = new Random();
                    int init = 0;
                    for(int i = 0; i<100; i++){
                        init += r.nextInt();
                        Thread.sleep(100);
                    }
                    return Integer.valueOf(init);
                }
            });
        }
        for(int i=0; i<10; i++){
            Future<Integer> future = cs.take();
            if(future != null){
                System.out.println(future.get());
            }
        }

    }


}
