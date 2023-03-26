package com.example.postservice.controller;

import com.example.postservice.exceptions.ResourceNotFoundException;
import com.example.postservice.entity.Post;
import com.example.postservice.repository.PostRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/posts")
public class PostController {

    @Autowired
    private PostRepository postRepository;

    @GetMapping
    public List<Post> getAllEmployees(){
        return postRepository.findAll();
    }

    // build create employee REST API
    @PostMapping
    public Post createPost(@RequestBody Post post) {
        return postRepository.save(post);
    }

    // build get employee by id REST API
    @GetMapping("{id}")
    public ResponseEntity<Post> getEmployeeById(@PathVariable  long id){
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id:" + id));
        return ResponseEntity.ok(post);
    }

//    // build update employee REST API
//    @PutMapping("{id}")
//    public ResponseEntity<Employee> updateEmployee(@PathVariable long id,@RequestBody Employee employeeDetails) {
//        Employee updateEmployee = employeeRepository.findById(id)
//                .orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id: " + id));
//
//        updateEmployee.setFirstName(employeeDetails.getFirstName());
//        updateEmployee.setLastName(employeeDetails.getLastName());
//        updateEmployee.setEmailId(employeeDetails.getEmailId());
//
//        employeeRepository.save(updateEmployee);
//
//        return ResponseEntity.ok(updateEmployee);
//    }

    // build delete employee REST API
    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> deletePost(@PathVariable long id){

        Post post = postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id: " + id));

        postRepository.delete(post);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }
}
