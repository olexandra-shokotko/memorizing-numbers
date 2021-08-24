package com.example.memorizingnumbers.repository;

import com.example.memorizingnumbers.domain.Line;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LineRepo extends JpaRepository<Line, Long> {
}