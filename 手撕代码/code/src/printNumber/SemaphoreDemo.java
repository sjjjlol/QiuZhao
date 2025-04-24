package printNumber;

import java.util.concurrent.*;

public class SemaphoreDemo {
    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(3); // 最多允许 3 个线程并发

        ExecutorService pool = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 10; i++) {
            int id = i;
            pool.submit(() -> {
                try {
                    semaphore.acquire();
                    System.out.println("线程 " + id + " 获取许可，执行任务");
                    Thread.sleep(1000); // 模拟执行
                    System.out.println("线程 " + id + " 释放许可");
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                } finally {
                    semaphore.release();
                }
            });
        }

        pool.shutdown();
    }
}