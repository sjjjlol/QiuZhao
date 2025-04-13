import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ProDucerConsumer {
    static BlockingQueue<Integer> queue  = new ArrayBlockingQueue<>(10);

    public static void main(String[] args) {
        Runnable producer = () -> {
            int value = 0;
            while (true){
                queue.add(value++);
                System.out.println("生产者生产：" + value);
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        };

        Runnable consumer = () -> {
            while (true){
                try {
                    Integer item = queue.take();
                    System.out.println("消费者" +Thread.currentThread().getName()+"消费：" + item);
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        };

        new Thread(producer).start();
        new Thread(consumer).start();
        new Thread(consumer).start();
    }
}
