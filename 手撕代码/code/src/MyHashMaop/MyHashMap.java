package MyHashMaop;

import java.util.Objects;

public class MyHashMap<K, V> {
    private static class Node<K, V> {
        final K key;
        V value;
        Node<K, V> next;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    private static final int DEFAULT_CAPACITY = 16; // 默认容量
    private static final float LOAD_FACTOR = 0.75f; // 负载因子
    private Node<K, V>[] table;
    private int size;

    public MyHashMap() {
        this.table = new Node[DEFAULT_CAPACITY];
    }

    // 计算哈希索引
    private int hash(K key) {
        return (key == null) ? 0 : (key.hashCode() & 0x7FFFFFFF) % table.length;
    }

    // put() 方法
    public void put(K key, V value) {
        int index = hash(key);
        Node<K, V> newNode = new Node<>(key, value);

        if (table[index] == null) {
            table[index] = newNode; // 直接存储
        } else {
            // 遍历链表，检查是否已有 key，若有则更新 value
            Node<K, V> current = table[index];
            while (true) {
                if (Objects.equals(current.key, key)) {
                    current.value = value; // 覆盖已有值
                    return;
                }
                if (current.next == null) break;
                current = current.next;
            }
            current.next = newNode; // 插入链表尾部
        }

        size++;
        if (size > table.length * LOAD_FACTOR) {
            resize(); // 负载因子超过阈值，进行扩容
        }
    }

    // get() 方法
    public V get(K key) {
        int index = hash(key);
        Node<K, V> current = table[index];

        while (current != null) {
            if (Objects.equals(current.key, key)) {
                return current.value; // 找到 key 返回 value
            }
            current = current.next;
        }

        return null; // key 不存在
    }

    // 扩容方法
    private void resize() {
        Node<K, V>[] oldTable = table;
        table = new Node[oldTable.length * 2]; // 扩容为原来的 2 倍
        size = 0;

        for (Node<K, V> head : oldTable) {
            while (head != null) {
                put(head.key, head.value); // 重新哈希后插入新表
                head = head.next;
            }
        }
    }

    public static void main(String[] args) {
        MyHashMap<String, Integer> map = new MyHashMap<>();
        map.put("apple", 1);
        map.put("banana", 2);
        map.put("cherry", 3);
        map.put("apple", 10); // 更新 key="apple" 的值

        System.out.println(map.get("apple")); // 输出: 10s
        System.out.println(map.get("banana")); // 输出: 2
        System.out.println(map.get("cherry")); // 输出: 3
        System.out.println(map.get("grape")); // 输出: null
    }
}
