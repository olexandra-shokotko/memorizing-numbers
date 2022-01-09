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
        int numberOfWords;
        if (signature.length() == 1) {
            numberOfWords = 1;
        }else {
            numberOfWords = signature.split("\\d+").length;
        }

        ArrayList<ArrayList<Line>> result = new ArrayList<>();

        for (int i = 0; i < lines.size(); i++) {
            ArrayList<Line> linesForCheck = getLinesNeededLength(lines, i, numberOfWords);

            if (linesForCheck == null) {
                break;
            }

            String linesSignature = "";
            for (Line line : linesForCheck) {
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

    public ArrayList<ArrayList<Line>> findVerse(String neededSignature, String language) {
        List<Verse> versesGivenLang = verseRepo.findByLanguage(language);
        ArrayList<ArrayList<Line>> result = new ArrayList<>();
        ArrayList<DetectedVerseDto> detectedVerses = new ArrayList<>();

        for (Verse verse : versesGivenLang) {
             result.addAll(findLinesWithSignature(new ArrayList<Line>(verse.getLines()), neededSignature));
        }

        return result;
    }

    public String getSignature(String number) {
        String[] strSplit = number.split("");
        ArrayList<String> numberArray = new ArrayList<String>(Arrays.asList(strSplit));
        int i = 0;

        for (String num : numberArray) {
            if (num.equals("0")) {
                numberArray.set(i, "10");
            }
            i++;
        }

        return String.join("_", numberArray);
    }
}
