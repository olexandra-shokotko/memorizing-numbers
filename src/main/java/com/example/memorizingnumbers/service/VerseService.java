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
import java.util.Set;

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

    private ArrayList<Line> getLinesNeededLength(ArrayList<Line> lines, int startIndex, int numberOfWords) {
        int wordsCounter = 0;
        int index = startIndex;
        ArrayList<Line> result = new ArrayList<>();

        while (index < lines.size() && wordsCounter < numberOfWords) {
            result.add(lines.get(index));
            wordsCounter += lines.get(index).getNumberOfWords();
            index++;
        }

        return wordsCounter >= numberOfWords ? result : null;
    }

    private ArrayList<ArrayList<Line>> findLinesWithSignature(ArrayList<Line> lines, String signature) {
        int numberOfWords = signature.split("\\d+").length;
        ArrayList<ArrayList<Line>> result = new ArrayList<>();

        for (int i = 0; i < lines.size(); i++) {
            ArrayList<Line> linesForCheck = getLinesNeededLength(lines, i, numberOfWords);

            if (linesForCheck == null) {
                break;
            }

            String linesSignature = "";
            for (Line line : linesForCheck) {
//                linesSignature = "";
                if (linesSignature.length() == 0) {
                    linesSignature = line.getSignature();
                }else {
                    linesSignature = String.join("_", linesSignature, line.getSignature());
                }
            }

            if (linesSignature.startsWith(signature)) {
                result.add(linesForCheck);
            }
        }

        return result;
    }

    public ArrayList<ArrayList<Line>> findVerse(String neededSignature) {
        List<Verse> verses = verseRepo.findAll();
        ArrayList<ArrayList<Line>> result = null;
        for (Verse verse : verses) {
             result = findLinesWithSignature(new ArrayList<Line>(verse.getLines()), neededSignature);
            if (result.size() > 0) {
                break;
            }
        }

        return result;
    }

    public String findVerse2(String phoneNumberSignature) {
        List<Line> lines = lineRepo.findAll();
        String signature = "";
        Long lineId;
        int counter;
        List<String> verse = new ArrayList<>();

        for (Line line : lines) {
            signature = line.getSignature();
            lineId = line.getId();
            counter = 1;

            while (signature.split("\\d+").length < 10) {
                signature = String.join("_", signature, lines.get(lines.indexOf(line) + counter).getSignature());
                counter++;
            }

            if (signature.startsWith(phoneNumberSignature)) {
                verse.add(lineRepo.findById(lineId).get().getText());

                for (int i = 0; i < counter - 1; i++) {
                    verse.add(lineRepo.findById(lineId + i + 1).get().getText());
                }
                return String.join("\n", verse);
            }
        }
        return "error";
    }

    public String getSignature(String phoneNumber) {
        String[] strSplit = phoneNumber.split("");
        ArrayList<String> phoneNumberArray = new ArrayList<String>(Arrays.asList(strSplit));

        return String.join("_", phoneNumberArray);
    }
}
