package inmmutable;

public class People {
    String name;

    //提供了
    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public People(String name) {
        this.name = name;
    }
}
