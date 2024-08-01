package com.wesleybertipaglia.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wesleybertipaglia.demo.entities.Demo;

public interface DemoRepository extends JpaRepository<Demo, Long> {
}
