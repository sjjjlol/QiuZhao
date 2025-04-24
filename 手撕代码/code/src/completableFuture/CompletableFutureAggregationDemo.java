package completableFuture;

import java.util.concurrent.*;

public class CompletableFutureAggregationDemo {

    public static void main(String[] args) throws Exception {
        ExecutorService executor = Executors.newFixedThreadPool(3);

        long start = System.currentTimeMillis();

        // 模拟异步调用 1：用户信息
        CompletableFuture<String> userFuture = CompletableFuture.supplyAsync(() -> {
            sleep(300);
            return "用户信息";
        }, executor);

        // 模拟异步调用 2：订单信息
        CompletableFuture<String> orderFuture = CompletableFuture.supplyAsync(() -> {
            sleep(400);
            return "订单信息";
        }, executor);

        // 模拟异步调用 3：活动信息
        CompletableFuture<String> activityFuture = CompletableFuture.supplyAsync(() -> {
            sleep(200);
            return "活动信息";
        }, executor);

        // 等待所有任务完成
        CompletableFuture<Void> allDone = CompletableFuture.allOf(userFuture, orderFuture, activityFuture);

        // join 会抛出 unchecked 异常，不需要声明 throws
        allDone.join();

        // 聚合结果
        String result = String.format("结果聚合 -> [%s] [%s] [%s]",
                userFuture.get(), orderFuture.get(), activityFuture.get());

        System.out.println(result);
        System.out.println("总耗时: " + (System.currentTimeMillis() - start) + "ms");

        executor.shutdown();
    }

    private static void sleep(long ms) {
        try { Thread.sleep(ms); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
    }
}