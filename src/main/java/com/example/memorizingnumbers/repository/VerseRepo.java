package com.example.memorizingnumbers.repository;

import com.example.memorizingnumbers.domain.Verse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VerseRepo extends JpaRepository<Verse, Long> {
    List<Verse> findByLanguage(String language);
}