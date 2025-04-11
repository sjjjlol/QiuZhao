import java.io.*;
import java.util.*;
import java.util.concurrent.*;

public class WordFrequencyCounter {
    private static final int THREAD_COUNT = 5; // 线程池大小
    private static final int TIME_LIMIT = 10; // 最大执行时间（秒）

    public static Map<String, Integer> countWordFrequency(List<File> files) throws InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(THREAD_COUNT);
        ConcurrentHashMap<String, Integer> wordCountMap = new ConcurrentHashMap<>();
        List<Future<?>> futures = new ArrayList<>();

        long startTime = System.currentTimeMillis();

        for (File file : files) {
            Future<?> future = executor.submit(() -> {
                try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                    String line;
                    while ((line = reader.readLine()) != null) {
                        for (String word : line.split(" ")) { // 按空格分词
                            if (!word.isEmpty()) {
                                wordCountMap.put(word, wordCountMap.getOrDefault(word, 0) + 1);
                            }
                        }

                        // 超时检测
                        if (System.currentTimeMillis() - startTime > TIME_LIMIT * 1000) {
                            return;
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            futures.add(future);
        }

        // 等待所有任务完成或超时
        for (Future<?> future : futures) {
            try {
                future.get(TIME_LIMIT, TimeUnit.SECONDS);
            } catch (TimeoutException e) {
                System.out.println("超时，返回当前统计结果...");
                break;
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }

        executor.shutdownNow(); // 终止所有线程
        return wordCountMap;
    }

    public static void main(String[] args) {
        List<File> files = new ArrayList<>();
        for (int i = 1; i <= 2; i++) {
            files.add(new File("src/"+ "file" + i + ".txt"));
        }

        try {
            Map<String, Integer> result = countWordFrequency(files);
            result.entrySet().stream()
                    .sorted((a, b) -> b.getValue().compareTo(a.getValue())) // 按词频排序
                    .forEach(entry -> System.out.println(entry.getKey() + ": " + entry.getValue()));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
