package Proxy;

public class UserServiceImpl implements UserService {
    @Override
    public void sayHello(String name) {
        System.out.println("Hello, " + name);
    }
}