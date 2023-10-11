package com.example.demo.Repository;

import com.example.demo.Model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {

}
