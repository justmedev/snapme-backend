package dev.justme.snapmebackend;

public class Example {
    private int age = 23;

    public static void main(String[] args) {
        Example example = new Example();
        example.setAge(23);
        System.out.println(example.getAge());
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getAge() {
        return age;
    }
}
