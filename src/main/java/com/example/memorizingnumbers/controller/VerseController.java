package com.example.memorizingnumbers.controller;

import com.example.memorizingnumbers.domain.Line;
import com.example.memorizingnumbers.repository.LineRepo;
import com.example.memorizingnumbers.repository.VerseRepo;
import com.example.memorizingnumbers.service.VerseService;
import jdk.nashorn.internal.objects.annotations.Property;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Controller
@PreAuthorize("hasAuthority('USER')")
public class VerseController {
    @Autowired
    private VerseService verseService;

    @Autowired
    private VerseRepo verseRepo;

    @Autowired
    private LineRepo lineRepo;

    @Property
    private String res;

    @GetMapping("/add-verse")
    public String addVerse() {
        return "verseAdd";
    }

    @PostMapping("/add-verse")
    public String storeVerse(@RequestParam String verseName,
                             @RequestParam String author,
                             @RequestParam String verse,
                             @RequestParam String language,
                             Map<String, Object> model) {
        verseService.addVerse(verseName, author, verse, language);

        return "redirect:/verses";
    }

    @GetMapping("/verses")
    public String versesList(Model model) {
        model.addAttribute("verses", verseRepo.findAll());

        return "versesList";
    }

    @GetMapping("/find-verse")
    public String main() {
        return "main";
    }

    @PostMapping("/find-verse")
    public String findVerse(@RequestParam String number,
                            @RequestParam String language,
                            Model model) {
        String signature = verseService.getSignature(number);
        ArrayList<ArrayList<Line>> result = verseService.findVerse(signature, language);

        model.addAttribute("result", result);
        model.addAttribute("number", number);
        model.addAttribute("lengthNumber", number.length());

        return "result";
    }
}