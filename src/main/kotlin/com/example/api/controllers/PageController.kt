package com.example.api.controllers

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/")
class PageController {

    @GetMapping
    fun getHomePage(): String {
        return "Homepage";
    }
    @GetMapping("error")
    fun getErrorPage(): String {
        return "Error-page";
    }
    @GetMapping("success")
    fun getSuccessPage(): String {
        return "Success-page";
    }
}