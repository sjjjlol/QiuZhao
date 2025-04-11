public class Main {
    public static void main(String[] args) {
        singleton instance1 = singleton.getInstance();
        singleton instance2 = singleton.getInstance();
        System.out.println(instance1.equals(instance2));
    }
}
