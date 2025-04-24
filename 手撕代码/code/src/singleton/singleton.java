package singleton;

public class singleton {
    private static volatile singleton instance;

    private singleton() {
    }

    public static singleton getInstance(){
        if(instance == null){
            synchronized (singleton.class){
                if(instance == null){
                    instance = new singleton();
                }
            }
        }
        return instance;
    }

    public static void main(String[] args) {
        singleton instance1 = singleton.getInstance();
        singleton instance2 = singleton.getInstance();
        System.out.println(instance1.equals(instance2));
    }
}
