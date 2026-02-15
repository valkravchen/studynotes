package com.dobrynya.studynotes.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/api/hello")
    public String hello() {
        return "Привет, StudyNotes!";
    }

    @GetMapping("/api/info")
    public String info() {
        return "StudyNotes — приложение для управления конспектами. Версия: 1.0";
    }

    @GetMapping("api/search")
    public String search(@RequestParam String query) {
        return "Ищем заметки по запросу: " + query;
    }
}
