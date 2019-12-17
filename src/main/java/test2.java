import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class test2 {
    public static void main(String[] args) {

        final Ticket ticket=new Ticket();
        new Thread(()->{for(int i=1;i<35;i++) ticket.sale();},"a").start();

    }
}





class Ticket{
    int num=30;
    Lock lock=new ReentrantLock();

    public void sale(){
        lock.lock();
        try{
            if(num>0)
            System.out.println(Thread.currentThread().getName()+"slave :" + num-- + "sheng yu:" + num);
        }
        finally {
            lock.unlock();
        }

    }
}
