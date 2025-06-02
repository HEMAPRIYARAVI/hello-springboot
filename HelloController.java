package com.example.hellospringboot;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;
import java.util.ArrayList;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import java.util.Optional;
import jakarta.validation.Valid;


@RestController
public class HelloController {
    private List<User> userList = new ArrayList<>();
    @GetMapping("/hello")
    public String sayHello() {
        return "Hello, Spring Boot!";
    }
    @PostMapping("/welcome")
    public String welcomeUser(@RequestBody String name) {
        return "Welcome, " + name + "!";
    }
    @PostMapping("/user")
    public String createUser(@Valid @RequestBody User user) {
        userList.add(user);
        return "User created: " + user.getName();
    }

    @PutMapping("/user")
    public String updateUser(@RequestBody User user) {
        return "Updated user: " + user.getName() + ", email: " + user.getEmail() + ", age: " + user.getAge();
    }
    @DeleteMapping("/user/{name}")
    public String deleteUser(@PathVariable String name) {
        Optional<User> userToDelete = userList.stream()
                .filter(user -> user.getName().equalsIgnoreCase(name))
                .findFirst();

        if (userToDelete.isPresent()) {
            userList.remove(userToDelete.get());
            return "User " + name + " deleted successfully!";
        } else {
            return "User " + name + " not found!";
        }
    }
    @GetMapping("/welcome")
    public String greetUser(@RequestParam String name) {
        return "Welcome, " + name + "!";
    }
    @GetMapping("/greet")
    public String greetUser(@RequestParam String name, @RequestParam int age) {
        return "Hello " + name + ", you are " + age + " years old!";
    }
    @PostMapping("/user/profile")
    public String getUserProfile(@RequestBody User user) {
        return "User received: " + user.getName() + ", " + user.getEmail() + ", " + user.getAge();
    }
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public class UserNotFoundException extends RuntimeException {
        public UserNotFoundException(String message) {
            super(message);
        }
    }

}


