package com.example.api.models

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDateTime

@Document("users")
data class User(
    @Id var id: String,
    var name: String,
    var email: String,
    var phonenumber: String,
    var password: String,
    var image: String? = null,
    var title: String? = null,
    val createdDate: LocalDateTime = LocalDateTime.now(),
    val modifiedDate: LocalDateTime = LocalDateTime.now()
    ) {
}