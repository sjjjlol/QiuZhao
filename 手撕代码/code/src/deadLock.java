public class deadLock {
    static final Object resource1 = new Object();
    static final Object resource2 = new Object();
    public static void main(String[] args) {
        Runnable task1 = () -> {
            synchronized (resource1){
                System.out.println("线程1获得了资源1");
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("线程1等待中。。。");
                synchronized (resource2) {
                    System.out.println("线程1获得了资源2");
                }
            }
        };
        Runnable task2 = () -> {
            synchronized (resource2){
                System.out.println("线程2获得了资源2");
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("线程2等待中。。。");
                synchronized (resource1) {
                    System.out.println("线程2获得了资源1");
                }
            }
        };
        new Thread(task1).start();
        new Thread(task2).start();
    }
}
