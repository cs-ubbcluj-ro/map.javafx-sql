package com.example.seminar322;

public class Person extends Entity {


    private final String name;

    public Person(int id, String name) {
        super(id);
        this.name = name;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }


}
