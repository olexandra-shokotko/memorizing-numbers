package com.example.memorizingnumbers.domain;


import org.hibernate.annotations.Type;

import javax.persistence.*;

@Entity
@Table(name="line")
public class Line {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @Type(type="text")
    private String text;

    private String signature;

    private boolean beginOfStanza;

    @ManyToOne
    @JoinColumn(name="verse_id", nullable=false)
    private Verse verse;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }
//    public String getLimitText(int len) {
//        return "abc";
//    }

    public void setText(String text) {
        this.text = text;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public boolean isBeginOfStanza() {
        return beginOfStanza;
    }

    public void setBeginOfStanza(boolean beginOfStanza) {
        this.beginOfStanza = beginOfStanza;
    }

    public Verse getVerse() {
        return verse;
    }

    public void setVerse(Verse verse) {
        this.verse = verse;
    }

    public int getNumberOfWords() {
        return signature.split("\\d+").length;
    }
}
