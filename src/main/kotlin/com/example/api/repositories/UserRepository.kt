package com.example.api.repositories

import com.example.api.models.User
import org.springframework.data.mongodb.repository.MongoRepository

interface UserRepository : MongoRepository<User, String> {

    fun findUserById(id: String): User;
    fun deleteUserById(id: String): User;

}