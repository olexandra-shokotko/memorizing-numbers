package com.example.memorizingnumbers.repository;

import com.example.memorizingnumbers.domain.Line;
import com.example.memorizingnumbers.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface LineRepo extends JpaRepository<Line, Long> {
//    Set<Line> findByVerseId(Long verseId);
}