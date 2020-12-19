package com.demo.desgin.builder;

//Builder 设计模式
//在参数很多的时候， 但是有时候只需要传递部分参数， 然后构建对象
public class Demo {
    public static void main(String[] args) {
        Person p = new Person.PersonBuilder()
                .buildPersonInfo(1,"mark")
                .buildPersonAge(30)
                .buildPersonSex("男")
                .buildPersonLoc("地球村","地球路")
                .builder();
        System.out.println("person------" + p.toString());
    }
}
