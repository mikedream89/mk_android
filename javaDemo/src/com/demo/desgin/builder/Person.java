package com.demo.desgin.builder;

public class Person {
    private int id;
    private String name;
    private int age;
    private String sex;
    private Location loc;
    private Person(){}
    public static class PersonBuilder {
        private Person person = new Person();

        public PersonBuilder buildPersonInfo(int id, String name){
            person.id = id;
            person.name = name;
            return this;
        }

        public PersonBuilder buildPersonAge(int age){
            person.age = age;
            return this;
        }

        public PersonBuilder buildPersonSex(String sex){
            person.sex = sex;
            return this;
        }

        public PersonBuilder buildPersonLoc(String addr, String city){
            person.loc = new Location(addr, city);
            return this;
        }

        public Person builder(){
            return  person;
        }
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", sex='" + sex + '\'' +
                ", loc=" + loc +
                '}';
    }
}


class Location {
    private String addr;
    private String city;

    public Location(String addr, String city){
        this.addr = addr;
        this.city = city;
    }

    @Override
    public String toString() {
        return "Location{" +
                "addr='" + addr + '\'' +
                ", city='" + city + '\'' +
                '}';
    }
}