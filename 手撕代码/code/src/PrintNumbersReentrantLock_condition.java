import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class PrintNumbersReentrantLock_condition {
    private static final int MAX_NUM = 10;
    private static int count = 1;
    private static final ReentrantLock lock = new ReentrantLock();
    private static final Condition[] conditions = new Condition[3];
    private static int turn = 0; // 用于标识当前该哪个线程执行

    public static void main(String[] args) {
        for (int i = 0; i < 3; i++) {
            conditions[i] = lock.newCondition();
        }

        Runnable printTask = () -> {
            int threadId = Integer.parseInt(Thread.currentThread().getName().substring(1)); // 获取线程编号
            while (true) {
                lock.lock();
                try {
                    while (count <= MAX_NUM && turn != threadId) {
                        conditions[threadId].await();
                    }

                    if (count > MAX_NUM) {
                        for (Condition condition : conditions) {
                            condition.signalAll(); // 唤醒所有线程，确保线程安全退出
                        }
                        break;
                    }

                    System.out.println("线程 " + Thread.currentThread().getName() + " -> " + count++);
                    turn = (turn + 1) % 3; // 轮到下一个线程执行
                    conditions[turn].signal(); // 唤醒下一个线程

                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        };

        new Thread(printTask, "T1").start();
        new Thread(printTask, "T2").start();
        new Thread(printTask, "T3").start();
    }
}
