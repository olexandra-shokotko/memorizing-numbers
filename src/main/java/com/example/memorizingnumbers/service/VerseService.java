package com.example.memorizingnumbers.service;

import com.example.memorizingnumbers.domain.Line;
import com.example.memorizingnumbers.domain.Verse;
import com.example.memorizingnumbers.repository.LineRepo;
import com.example.memorizingnumbers.repository.VerseRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class VerseService {
    @Autowired
    private VerseRepo verseRepo;

    @Autowired
    private LineRepo lineRepo;

    public void addVerse(String name, String author, String text, String language) {
        Verse verse = new Verse();
        verse.setAuthor(author);
        verse.setName(name);
        verse.setLanguage(language);
        verseRepo.save(verse);
        String[] lines = text.split(System.getProperty("line.separator"));

        for (String l : lines) {
            Line line = createLine(l, verse);
            verse.addLine(line);
        }

        lineRepo.flush();
        verseRepo.flush();
    }

    public Line createLine(String text, Verse verse) {
        Line line = new Line();
        line.setText(text);
        line.setSignature(calcSignature(text));
        line.setVerse(verse);

        lineRepo.save(line);
        return line;
    }

    private String calcSignature(String text) {
        String text1 = text.replaceAll("[^a-zA-Zа-яА-Я\\s]","");
        String[] words = text1.split("\\s+");
        ArrayList<String> wordsLen = new ArrayList<>();

        for (String w : words) {
            wordsLen.add(String.valueOf(w.length()));
        }

        return String.join("_", wordsLen);
    }

    public void findVerse() {
    }

    public String getSignature(String phoneNumber) {
        String[] strSplit = phoneNumber.split("");
        ArrayList<String> phoneNumberArray = new ArrayList<String>(Arrays.asList(strSplit));

        return String.join("_", phoneNumberArray);
    }
}
