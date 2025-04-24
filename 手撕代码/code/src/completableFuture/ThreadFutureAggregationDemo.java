package completableFuture;

import java.util.concurrent.*;

public class ThreadFutureAggregationDemo {

    public static void main(String[] args) throws Exception {
        ExecutorService executor = Executors.newFixedThreadPool(3);

        long start = System.currentTimeMillis();

        // 1. 提交任务（用户信息）
        Future<String> userFuture = executor.submit(() -> {
            sleep(300);
            return "用户信息";
        });

        // 2. 提交任务（订单信息）
        Future<String> orderFuture = executor.submit(() -> {
            sleep(400);
            return "订单信息";
        });

        // 3. 提交任务（活动信息）
        Future<String> activityFuture = executor.submit(() -> {
            sleep(200);
            return "活动信息";
        });

        // 4. 阻塞等待结果
        String user = userFuture.get();       // 阻塞直到任务1完成
        String order = orderFuture.get();     // 阻塞直到任务2完成
        String activity = activityFuture.get(); // 阻塞直到任务3完成

        // 5. 聚合输出
        String result = String.format("结果聚合 -> [%s] [%s] [%s]", user, order, activity);
        System.out.println(result);
        System.out.println("总耗时: " + (System.currentTimeMillis() - start) + "ms");

        executor.shutdown();
    }

    private static void sleep(long ms) {
        try { Thread.sleep(ms); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
    }
}