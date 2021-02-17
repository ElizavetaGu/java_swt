package ru.stqa.pft.sandbox;

public class Equality {
    public static void main (String[] args){

        //в переменной s2 хранится ссылка на объект s1, оператор == сравнивает ссылки и возвращает true
        //String s1 = "firefox";
        //String s2 = s1;

        //в s2 хранится ссылка на новый объект, которая отличается от ссылки на s1,
        //поэтому оператор == возвращется false
        //String s1 = "firefox";
        //String s2 = new String(s1);

        //компилятор оптимизирует код, поэтому все переменные, которые ссылаются на одну и ту же
        //литеральную строку, ссылаются на один объект
        String s1 = "firefox";
        String s2 = "firefox";

        System.out.println(s1 == s2);
        System.out.println(s1.equals(s2));
    }
}
