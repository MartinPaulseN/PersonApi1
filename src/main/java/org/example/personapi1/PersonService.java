package org.example.personapi1;

import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.NoSuchElementException;

@Service
public class PersonService {

    private final PersonRepository repository;

    public PersonService(PersonRepository repository) {
        this.repository = repository;
    }

    public Person createPerson(Person person) {
        if (repository.existsById(person.getId())) {
            throw new IllegalArgumentException("Person with ID already exists");
        }
        return repository.save(person);
    }

    public Optional<Person> getPersonById(Long id) {
        return repository.findById(id);
    }

    public List<Person> getAllPersons() {
        return repository.findAll();
    }

    public List<Person> getPersonsByName(String name) {
        return repository.findByName(name);
    }

    public Person updatePerson(Long id, Person updatedPerson) {
        if (!repository.existsById(id)) {
            throw new NoSuchElementException("Person not found");
        }
        updatedPerson.setId(id);
        return repository.save(updatedPerson);
    }

    public void deletePerson(Long id) {
        repository.deleteById(id);
    }
}
