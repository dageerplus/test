package ThreadPoolExecutor;

import java.util.concurrent.*;


public class MyThreadPoolExecutor {
    public static void main(String[] args) {

        ExecutorService threadPool=new ThreadPoolExecutor(
                2,
                5,
                1L,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<Runnable>(3),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy());
        try {
            for (int i = 0; i < 8; i++) {
                final int tempI = i;
                threadPool.execute(() -> {
                    System.out.println(Thread.currentThread().getName() + "受理" + "客户" + tempI);
                });
            }
        }finally {
            threadPool.shutdown();
        }
    }
}
