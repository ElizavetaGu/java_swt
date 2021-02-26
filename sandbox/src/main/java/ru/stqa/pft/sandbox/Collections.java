package ru.stqa.pft.sandbox;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Collections {
    public static void main (String[] args) {
        //МАССИВЫ
        String[] langs = new String[4];
        langs[0] = "Java";
        langs[1] = "C#";
        langs[2] = "Python";
        langs[3] = "PHP";

        String[] langs2 = {"Java", "C#", "Python", "PHP"};

        //перебор элементов массива с помощью классического цикла
        for (int i = 0; i < langs.length; i++){
            System.out.println("Я хочу выучить " + langs[i]);
        }
        //специальный синтаксис для перебора элементов массива
        for (String l : langs2){ //l - это ссылка на элемент массива, она последовательно указывает на все
            //элементы этой коллекции
            System.out.println("Я хочу выучить " + l);
        }

        //КОЛЛЕКЦИИ - Список
        //слева указан интерфейс, а справа конкретный класс, который реализует этот интерфейс
        List<String> languages = new ArrayList<String>();
        //размер списка меняется динамически и заранее не определен
        languages.add("Java");
        languages.add("C#");

        //класс, который содержит методы для преобразования массивов в списки
        List<String> languages2 = Arrays.asList("Java", "C#", "Python", "PHP");

        for (String l : languages){ //l - это ссылка на элемент списка, она последовательно указывает на все
            //элементы этой коллекции
            System.out.println("Я хочу выучить " + l);
        }
    }
}
