package org.example.personapi1;

import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Repository
public class PersonRepository {

    private final Map<Long, Person> personStorage = new ConcurrentHashMap<>();

    public Person save(Person person) {
        personStorage.put(person.getId(), person);
        return person;
    }

    public Optional<Person> findById(long id) {
        return Optional.ofNullable(personStorage.get(id));
    }

    public List<Person> findAll() {
        return new ArrayList<>(personStorage.values());
    }

    public List<Person> findByName(String name) {
        return personStorage.values().stream()
                .filter(p -> p.getName().equalsIgnoreCase(name))
                .collect(Collectors.toList());
    }

    public void deleteById(long id) {
        personStorage.remove(id);
    }

    public boolean existsById(long id) {
        return personStorage.containsKey(id);
    }
}
