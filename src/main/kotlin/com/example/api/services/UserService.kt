package com.example.api.services

import com.example.api.models.User
import com.example.api.repositories.UserRepository
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import java.util.*

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

        if (user.name !== ""){
            oldUser.name = user.name;
        }
        if (user.email !== ""){
            oldUser.email = user.email;
        }
        if (user.phonenumber !== ""){
            oldUser.phonenumber = user.phonenumber;
        }
        if (user.image !== ""){
            oldUser.image = user.image;
        }
        if (user.password !== ""){
            oldUser.password = user.password;
        }
        if (user.title !== ""){
            oldUser.title = user.title;
        }
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