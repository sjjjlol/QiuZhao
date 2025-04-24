package MyArrayList;

public class test {
    // 简单测试
    public static void main(String[] args) {
        MyArrayList<String> list = new MyArrayList<String>();
        list.add("A");
        list.add("B");
        list.add("C");
        System.out.println(list); // [A, B, C]

        list.remove(1);
        System.out.println(list); // [A, C]

        System.out.println(list.get(1)); // C
    }
}
