package com.app.ecommerce;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/users")
public class UserController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        return new ResponseEntity<>(userService.fetchAllUsers(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable UUID id){
        return userService.fetchUser(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<String> createUser(@RequestBody User user){
        userService.addUser(user);
        return ResponseEntity.ok("User added!!");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateUser(@PathVariable UUID id,
                                             @RequestBody User updateUser) {
        boolean updated = userService.updateUser(id, updateUser);
        if(updated)
            return ResponseEntity.ok("User updated successfully!!");
        return ResponseEntity.notFound().build();
    }
}
