package com.example.proj1.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.proj1.model.project1;
import com.example.proj1.repository.project1repository;

@RestController
@RequestMapping("/api")
public class project1Controller {

    @Autowired
    project1repository project1Repository;

    @GetMapping("/show_all")

    public List<project1> getAllTutorials(){
        return (List<project1>) project1Repository.findAll();
    }

        @PostMapping("/create")
    public ResponseEntity<project1> createTutorial(@RequestBody project1 Project1){
        project1 _Project1= project1Repository
        .save(new project1(Project1.getFirstname(),Project1.getLastname(),Project1.getPassword()));

        return new ResponseEntity<project1>(_Project1, HttpStatus.CREATED);
    }

    @PutMapping("/insert/{id}")
    public ResponseEntity<project1> updateproject1(@PathVariable("id") Long id,@RequestBody project1 Project1){
        Optional<project1> __Project1 = project1Repository.findById(id);
        if(__Project1.isPresent()){
            project1 _Project1 = __Project1.get();
            _Project1.setFirstname(Project1.getFirstname());
            _Project1.setLastname(Project1.getLastname());
            _Project1.setPassword(Project1.getPassword());

            return new ResponseEntity<> (project1Repository.save(_Project1),HttpStatus.OK);
        }
        else{
            return new ResponseEntity<> (HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete_all")
    public ResponseEntity<HttpStatus> deleteAllproject1(){
        project1Repository.deleteAll();
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    
}
