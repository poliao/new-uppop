package com.example.demo.controller;
import com.example.demo.Model.Person;
import com.example.demo.Repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/persons")
public class PersonController {

    private final PersonRepository personRepository;

    @Autowired
    public PersonController(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    // สร้าง Person
    @PostMapping
    public ResponseEntity<Person> createPerson(@RequestBody Person person) {
        Person createdPerson = personRepository.save(person);
        return new ResponseEntity<>(createdPerson, HttpStatus.CREATED);
    }

    // ดึงข้อมูล Person ทั้งหมด
    @GetMapping
    public ResponseEntity<List<Person>> getAllPersons() {
        List<Person> persons = personRepository.findAll();
        return new ResponseEntity<>(persons, HttpStatus.OK);
    }

    // ดึงข้อมูล Person ตาม ID
    @GetMapping("/{id}")
    public ResponseEntity<Person> getPersonById(@PathVariable Long id) {
        return personRepository.findById(id)
                .map(person -> new ResponseEntity<>(person, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // อัพเดท Person ตาม ID
    @PutMapping("/{id}")
    public ResponseEntity<Person> updatePerson(@PathVariable Long id, @RequestBody Person updatedPerson) {
        return personRepository.findById(id)
                .map(existingPerson -> {
                    updatedPerson.setID_user(existingPerson.getID_user());
                    Person savedPerson = personRepository.save(updatedPerson);
                    return new ResponseEntity<>(savedPerson, HttpStatus.OK);
                })
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // ลบ Person ตาม ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePerson(@PathVariable Long id) {
        personRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
