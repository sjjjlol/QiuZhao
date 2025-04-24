package MyArrayList;

import java.util.Arrays;

public class MyArrayList<E> {

    private Object[] data; // 存储元素的数组
    private int size;      // 实际元素个数
    private static final int DEFAULT_CAPACITY = 10;

    public MyArrayList() {
        data = new Object[DEFAULT_CAPACITY];
    }

    public void add(E element) {
        ensureCapacity();
        data[size++] = element;
    }

    public E get(int index) {
        rangeCheck(index);
        return (E) data[index];
    }

    public E remove(int index) {
        rangeCheck(index);
        E removed = (E) data[index];
        // 把后面的元素往前挪
        System.arraycopy(data, index + 1, data, index, size - index - 1);
        data[--size] = null; // 防止内存泄漏
        return removed;
    }

    public int size() {
        return size;
    }

    private void ensureCapacity() {
        if (size == data.length) {
            int newCapacity = data.length * 2;
            data = Arrays.copyOf(data, newCapacity);
        }
    }

    private void rangeCheck(int index) {
        if (index < 0 || index >= size) throw new IndexOutOfBoundsException("Index: " + index);
    }

    @Override
    public String toString() {
        return Arrays.toString(Arrays.copyOf(data, size));
    }
}
