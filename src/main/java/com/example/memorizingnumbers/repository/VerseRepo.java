package com.example.memorizingnumbers.repository;

import com.example.memorizingnumbers.domain.Verse;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VerseRepo extends JpaRepository<Verse, Long> {
//    List<Verse> findVersesByLanguage(String language);
}