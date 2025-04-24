package printNumber;

public class PrintNumbersSync_wait {
    static final int MAX = 10;
    static int count = 1;
    static final Object lock = new Object();
    static int turn = 1; // 控制轮到哪个线程

    public static void main(String[] args) {
        Runnable printTask = () -> {
            int threadNum = Integer.parseInt(Thread.currentThread().getName().substring(1)); // 获取线程编号

            while (true) {
                synchronized (lock) {
                    // 不该当前线程打印，则 wait
                    while (count <= MAX && turn != threadNum) {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }

                    // 退出条件必须放在 synchronized 代码块内部
                    if (count > MAX) {
                        lock.notifyAll(); // 唤醒其他线程，确保它们能够正常退出
                        break;
                    }

                    System.out.println("线程 " + Thread.currentThread().getName() + " -> " + count++);
                    turn = turn % 3 + 1; // 轮到下一个线程执行
                    try {
                        Thread.sleep(500); // 让线程休眠，模拟执行间隔
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    lock.notifyAll(); // 唤醒其他线程
                }

            }
        };

        new Thread(printTask, "T1").start();
        new Thread(printTask, "T2").start();
        new Thread(printTask, "T3").start();
    }
}
