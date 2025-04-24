package inmmutable;

public final class student {
    private final int age;
    private final People people;

    public student(int age, People people) {
        this.age = age;
        this.people = new People(people.getName());
    }

    public int getAge() {
        return age;
    }

    public People getPeople() {
        return new People(this.people.getName());
    }
}
