class one {
    private int i = 0;

    public synchronized void in() throws InterruptedException {

            while (i != 0) {
                this.wait();
            }
            ++i;
            System.out.println(Thread.currentThread().getName() + " " + i);
            this.notifyAll();//自动释放控制权
        }


    public synchronized void de() {

            while (i == 0) {
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            --i;
            System.out.println(Thread.currentThread().getName() + " " + i);
            this.notifyAll();//自动释放控制权
        }
    }

public  class ThreadWaitNotify {
    public static void main(String[] args) throws Exception {
        one o = new one();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    o.in();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "a").start();

        new Thread(()->{for (int i=0;i<10;i++){
            o.de();

        }
        },"b").start();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    o.in();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "c").start();

        new Thread(()->{for (int i=0;i<10;i++){
            o.de();

        }
        },"d").start();
    }
}



