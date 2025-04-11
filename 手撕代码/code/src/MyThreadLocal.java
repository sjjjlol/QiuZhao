import java.util.WeakHashMap;

public class MyThreadLocal<T> {
    private final WeakHashMap<Thread, T> threadLocalMap = new WeakHashMap<>();

    // 获取当前线程的值
    public T get() {
        synchronized (threadLocalMap) {
            return threadLocalMap.get(Thread.currentThread());
        }
    }

    // 设置当前线程的值
    public void set(T value) {
        synchronized (threadLocalMap) {
            threadLocalMap.put(Thread.currentThread(), value);
        }
    }

    // 移除当前线程的变量，防止内存泄漏
    public void remove() {
        synchronized (threadLocalMap) {
            threadLocalMap.remove(Thread.currentThread());
        }
    }

    public static void main(String[] args) {
        MyThreadLocal<Integer> myThreadLocal = new MyThreadLocal<>();

        Runnable task = () -> {
            myThreadLocal.set((int) (Math.random() * 100));
            System.out.println(Thread.currentThread().getName() + " -> " + myThreadLocal.get());
        };

        Thread t1 = new Thread(task, "Thread-1");
        Thread t2 = new Thread(task, "Thread-2");

        t1.start();
        t2.start();
    }
}
