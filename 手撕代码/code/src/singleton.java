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
}
