import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Tickets{
    int i=0;
    private Lock lock=new ReentrantLock();
    private Condition condition=lock.newCondition();

    public void in(){
        lock.lock();

            try {
                while(i !=0) {
                    condition.await();

                }
                ++i;
                System.out.println(Thread.currentThread().getName()+" :"+i);
                condition.signalAll();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            finally {
               lock.unlock();
            }

    }
    public void out(){
        lock.lock();

            try {
                while(i ==0) {
                    condition.await();
                }
                --i;
                System.out.println(Thread.currentThread().getName()+" :"+i);
                condition.signalAll();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            finally {
             lock.unlock();
            }

    }
}

public class lock {
    public static void main(String[] args) {
        Tickets tickets=new Tickets();
        new Thread(()->{ for (int i=0;i<10;i++) tickets.in();},"a").start();
        new Thread(()->{for (int i=0;i<10;i++) tickets.out();},"b").start();
        new Thread(()->{ for (int i=0;i<10;i++)tickets.out();},"c").start();
        new Thread(()->{for (int i=0;i<10;i++) tickets.in();},"d").start();

    }
}


