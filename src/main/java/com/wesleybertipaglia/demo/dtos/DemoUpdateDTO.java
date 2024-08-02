package com.wesleybertipaglia.demo.dtos;

import java.util.Optional;

import jakarta.validation.constraints.Size;

public record DemoUpdateDTO(
        @Size(max = 100, message = "Title must be less than 100 characters") Optional<String> title,
        @Size(max = 500, message = "Description must be less than 500 characters") Optional<String> description) {
}