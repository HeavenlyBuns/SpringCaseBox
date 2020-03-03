package com.shr.springboot.starter;

import com.alibaba.fastjson.JSONArray;

public class JsonTest {

    static class Person{
        String name;
        String age;

        public Person(String name, String age) {
            this.name = name;
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getAge() {
            return age;
        }

        public void setAge(String age) {
            this.age = age;
        }
    }

    public static void main(String[] args) {
        /*JSONArray array = new JSONArray();
        Person fff = new Person("222", "fff");
        array.add(fff);
        Person f2 = new Person("1", "gg");
        array.add(f2);

        System.out.println(array.toJSONString());*/

        int price =  10;
        int max = 200000;

        int fen = 2500 *12 * 2;
        System.out.println(fen);


    }

}
