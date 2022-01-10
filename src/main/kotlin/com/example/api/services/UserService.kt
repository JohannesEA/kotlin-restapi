package com.example.api.services

import com.example.api.models.User
import com.example.api.repositories.UserRepository
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
class UserService (private val userRepository: UserRepository) {

    fun findAllUsers(): ResponseEntity<List<User>> {
         return ResponseEntity.ok(userRepository.findAll());
    }

    fun findUserById(id: String): User {
       return userRepository.findUserById(id);
    }

    fun createUser(user: User): ResponseEntity<User> {
       return ResponseEntity.ok(userRepository.save(user));
    }

    fun updateUser(id: String, user: User): ResponseEntity<User> {
        var oldUser = userRepository.findUserById(id);
            println(oldUser.toString())
            user.id = id
            oldUser.name = user.name;
            oldUser.email = user.email;
            oldUser.phonenumber = user.phonenumber;
            oldUser.image = user.image;
            oldUser.password = user.password;
            oldUser.title = user.title;
        return ResponseEntity.ok(userRepository.save(user));
    }

    fun deleteAllUsers():  ResponseEntity<String> {
        userRepository.deleteAll();
        return ResponseEntity.ok("All users is deleted.");
    }

    fun deleteUser(id: String): ResponseEntity<String> {
        userRepository.deleteUserById(id);
        return ResponseEntity.ok("User with id $id is now deleted.");
    }
}