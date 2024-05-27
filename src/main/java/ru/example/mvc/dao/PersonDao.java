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

        personList.add(new Person(++PEOPLE_COUNT, "Artem", 32, "ark@gmail.com"));
        personList.add(new Person(++PEOPLE_COUNT, "Tanya", 30, "deg@gmail.com"));
        personList.add(new Person(++PEOPLE_COUNT, "Pol", 29, "pol@gmail.com"));
        personList.add(new Person(++PEOPLE_COUNT, "Parker", 43, "park@gmail.com"));
        personList.add(new Person(++PEOPLE_COUNT, "Kate", 15, "kat@gmail.com"));
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

    public void update(int id, Person person) {
        Person personToBeUpdated = show(id);
        personToBeUpdated.setName(person.getName());
        personToBeUpdated.setAge(person.getAge());
        personToBeUpdated.setEmail(person.getEmail());
    }

    public void delete(int id) {
        personList.removeIf(e -> e.getId() == id);
    }

}
