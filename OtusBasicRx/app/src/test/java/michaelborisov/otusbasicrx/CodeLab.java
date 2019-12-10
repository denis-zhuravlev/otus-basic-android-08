package michaelborisov.otusbasicrx;


import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import io.reactivex.Observable;

public class CodeLab {

    String result = "";

    ArrayList<String> cities = new ArrayList<String>(
            Arrays.asList("Москва", "Лондон", "Прага", "Новосибирск", "Минск", "Осло"));

    ArrayList<Integer> ages = new ArrayList<Integer>(
            Arrays.asList(12, 8, 9, 10, 22, 54, 27, 33, 31, 38, 39));

    ArrayList<String> names = new ArrayList<String>(
            Arrays.asList("Никита", "Михаил", "Андрей", "Василий", "Давид", "Леонид"));

    ArrayList<String> surnames = new ArrayList<String>(
            Arrays.asList("Пушкин", "Калашников", "Лермонтов", "Маяковский"));

    @Before
    public void init() {
        result = "";
    }

    @Test
    public void operatorsTest() {

        Observable.fromIterable(createUsers())
                // Отфильровать только тех кто живет в Москве
                // TODO
                // Отфильровать только тех кто старше 21
                // TODO
                // Исключить однофамильцев
                // TODO
                // Вывести полное имя
                // TODO
                .subscribe(i -> System.out.println(i));
    }

    public ArrayList<User> createUsers() {
        ArrayList<User> users = new ArrayList<>();

        for (int i = 0; i < 100; i++) {
            users.add(new User(ages.get(rnd(ages.size())), names.get(rnd(names.size())), surnames.get(rnd(surnames.size())),
                    cities.get(rnd(cities.size()))));

        }

        return users;

    }


    public int rnd(int size) {
        int rnd = new Random().nextInt(size);
        return rnd;
    }

}
