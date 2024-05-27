package ru.example.mvc.dao;

import org.postgresql.Driver;
import org.springframework.stereotype.Component;
import ru.example.mvc.models.Person;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
public class PersonDao {
    public static int PEOPLE_COUNT;

//    private static final String URL = "jdbc:postgresql://localhost:5432/alishev_db";
//    private static final String USERNAME = "postgres";
//    private static final String PASSWORD = "Achinsk1991";
//
//    private static Connection connection;
//
//    {
//        try {
//            Class.forName("org.postgresql.Driver");
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//
//        try {
//            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }

    private List<Person> personList;

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
