package com.example.memorizingnumbers.controller;

import com.example.memorizingnumbers.repository.LineRepo;
import com.example.memorizingnumbers.repository.VerseRepo;
import com.example.memorizingnumbers.service.VerseService;
import jdk.nashorn.internal.objects.annotations.Property;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.Map;

@Controller
@PreAuthorize("hasAuthority('ADMIN')")
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

    @GetMapping("/result")
    public String resultVerse(Model model) {
//        model.addAttribute("res", res);
        return "result";
    }

    @GetMapping("/find-verse")
    public String main() {
        return "main";
    }

    @PostMapping("/find-verse")
    public String findVerse(@RequestParam String phoneNumber,
                             /*@RequestParam String language,
                             @RequestParam String length,*/
                             Map<String, Object> model
                             /*Model model*/) {
        String signature = verseService.getSignature(phoneNumber);
        String res = verseService.findVerse(signature);

        return "redirect:/result";
    }
}