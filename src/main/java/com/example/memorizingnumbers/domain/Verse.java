package com.example.memorizingnumbers.domain;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="verse")
public class Verse {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    private String author;
    private String name;
    private String language;

    @OneToMany(mappedBy = "verse")
    private Set<Line> lines;

    public Verse() {
        this.lines = new HashSet<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public Set<Line> getLines() {
        return lines;
    }

    public void setLines(Set<Line> lines) {
        this.lines = lines;
    }

    public void addLine(Line line) {
        lines.add(line);
    }
}