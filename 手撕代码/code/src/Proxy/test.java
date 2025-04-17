package Proxy;

import org.junit.Test;

public class test {
    @Test
    public void testProxy(){
        DynamicProxy proxy = new DynamicProxy();
        UserService userService = (UserService) proxy.getInstance(new UserServiceImpl());
        userService.sayHello("小翻");
    }
}
