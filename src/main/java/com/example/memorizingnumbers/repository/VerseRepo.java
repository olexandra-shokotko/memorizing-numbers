package com.example.memorizingnumbers.repository;

import com.example.memorizingnumbers.domain.Verse;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VerseRepo extends JpaRepository<Verse, Long> {
}