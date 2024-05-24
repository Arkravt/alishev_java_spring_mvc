package ru.example.mvc.dao;

import org.springframework.stereotype.Component;
import ru.example.mvc.models.Person;

import java.util.ArrayList;
import java.util.List;

@Component
public class PersonDao {

    public static int PEOPLE_COUNT;
    List<Person> personList;

    {
        personList = new ArrayList<>();

        personList.add(new Person(++PEOPLE_COUNT, "Artem"));
        personList.add(new Person(++PEOPLE_COUNT, "Tanya"));
        personList.add(new Person(++PEOPLE_COUNT, "Pol"));
        personList.add(new Person(++PEOPLE_COUNT, "Parker"));
        personList.add(new Person(++PEOPLE_COUNT, "Kate"));
    }

    public List<Person> allPeople() {
        return personList;
    }

    public Person show(int id) {
        return personList.stream()
                .filter(f -> f.getId() == id)
                .findAny()
                .orElse(null);
    }

    public void save(Person person) {
        person.setId(++PEOPLE_COUNT);
        personList.add(person);
    }

}
