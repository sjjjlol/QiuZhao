package IODemos;

import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.stream.Collectors;

public class BatchFirstLineReader {

    private static final int THREAD_COUNT = Runtime.getRuntime().availableProcessors() * 2;
    private static final int BATCH_WRITE_SIZE = 1000;
    private static final Path LOG_FILE = Paths.get("first_lines.log");

    public static void main(String[] args) throws InterruptedException, IOException {
        List<Path> files = Files.walk(Paths.get("/data/files/"))
                .filter(Files::isRegularFile)
                .collect(Collectors.toList());

        ExecutorService executor = Executors.newFixedThreadPool(THREAD_COUNT);
        BlockingQueue<String> lineQueue = new LinkedBlockingQueue<>();

        // 批量写日志线程
        Thread writerThread = new Thread(() -> {
            try (BufferedWriter writer = Files.newBufferedWriter(LOG_FILE)) {
                List<String> buffer = new ArrayList<>(BATCH_WRITE_SIZE);
                while (true) {
                    String line = lineQueue.poll(3, TimeUnit.SECONDS);
                    if (line != null) {
                        buffer.add(line);
                    }
                    if (buffer.size() >= BATCH_WRITE_SIZE || (line == null && !buffer.isEmpty())) {
                        for (String l : buffer) writer.write(l + "\n");
                        writer.flush();
                        buffer.clear();
                    }
                    if (line == null && Thread.currentThread().isInterrupted()) break;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        writerThread.start();

        // 并发读取任务提交
        CountDownLatch latch = new CountDownLatch(files.size());
        for (Path file : files) {
            executor.submit(() -> {
                try (BufferedReader reader = Files.newBufferedReader(file)) {
                    String firstLine = reader.readLine();
                    if (firstLine != null) {
                        lineQueue.offer(file.getFileName() + ": " + firstLine);
                    }
                } catch (IOException ignored) {
                } finally {
                    latch.countDown();
                }
            });
        }

        latch.await();
        writerThread.interrupt(); // 通知写线程可以退出
        executor.shutdown();
        System.out.println("读取完毕，共处理文件数: " + files.size());
    }
}