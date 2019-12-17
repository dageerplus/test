package ThreadPoolExecutor;

import sun.security.krb5.internal.Ticket;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;



public class threadPoolDemo {
    public static void main(String[] args) {

        ExecutorService executorService = Executors.newCachedThreadPool();
        try {
            for (int i = 0; i < 25; i++) {

                final int tempI = i;
                executorService.execute(() -> {
                    System.out.println(Thread.currentThread().getName() + "受理第 " + tempI + " " + "业务");
                });
            }
        }finally {
            executorService.shutdown();
        }
    }
}
