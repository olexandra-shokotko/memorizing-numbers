package com.example.memorizingnumbers.controller;

import com.example.memorizingnumbers.service.VerseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@Controller
@PreAuthorize("hasAuthority('ADMIN')")
public class VerseController {
    @Autowired
    private VerseService verseService;

    @GetMapping("/add-verse")
    public String addVerse() {
        try {
            verseService.addVerse(loadFromFile());

        }catch (Exception ex) {
            return "temp";
        }

        return "temp";
    }

    public String loadFromFile() throws IOException {
        return new String(Files.readAllBytes(Paths.get("src/main/resources/verse_en.txt")));
    }
}