package com.example.api.controllers

import com.example.api.models.User;
import com.example.api.services.UserService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("api/users")
class UserController (private val userService: UserService) {

    @GetMapping
    fun getUsers(): ResponseEntity<List<User>> {
        return userService.findAllUsers();
    }
    @GetMapping("/{id}")
    fun getUserById(@PathVariable id: String): User {
        return userService.findUserById(id);
    }
    @PostMapping
    fun postUser(@RequestBody user: User): ResponseEntity<User> {
        return userService.createUser(user);
    }
    @PutMapping("/{id}")
    fun putUser(@PathVariable id: String, @RequestBody user: User): ResponseEntity<User> {
        return userService.updateUser(id, user);
    }
    @DeleteMapping
    fun deleteUsers(): ResponseEntity<String>{
        return userService.deleteAllUsers();
    }
    @DeleteMapping("/{id}")
    fun deleteUserById(@PathVariable id: String): ResponseEntity<String> {
        return userService.deleteUser(id);
    }








}