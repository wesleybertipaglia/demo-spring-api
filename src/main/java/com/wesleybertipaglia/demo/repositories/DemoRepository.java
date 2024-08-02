package com.wesleybertipaglia.demo.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.wesleybertipaglia.demo.entities.Demo;

public interface DemoRepository extends JpaRepository<Demo, Long> {
    Page<Demo> findAllByTitleContaining(String title, Pageable pageable);

    Page<Demo> findAllByDescriptionContaining(String description, Pageable pageable);

    Page<Demo> findAllByTitleContainingAndDescriptionContaining(String title, String description, Pageable pageable);
}
