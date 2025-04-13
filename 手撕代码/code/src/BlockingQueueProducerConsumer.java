import java.util.concurrent.*;

public class BlockingQueueProducerConsumer {

    private static final int QUEUE_CAPACITY = 5;
    private static final BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(QUEUE_CAPACITY);

    public static void main(String[] args) {
        // 生产者线程
        Runnable producer = () -> {
            int value = 0;
            while (true) {
                try {
                    queue.put(value);
                    System.out.println("生产者生产：" + value);
                    value++;
                    Thread.sleep(200); // 模拟生产耗时
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    break;
                }
            }
        };

        // 消费者线程
        Runnable consumer = () -> {
            while (true) {
                try {
                    int value = queue.take();
                    System.out.println("消费者消费：" + value);
                    Thread.sleep(300); // 模拟消费耗时
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    break;
                }
            }
        };

        // 启动线程
        new Thread(producer, "Producer-1").start();
        new Thread(producer, "Producer-2").start();
        new Thread(consumer, "Consumer-1").start();
    }
}