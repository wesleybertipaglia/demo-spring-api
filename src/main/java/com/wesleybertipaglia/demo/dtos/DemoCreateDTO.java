package com.wesleybertipaglia.demo.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record DemoCreateDTO(
        @NotBlank(message = "Title cannot be blank") @Size(max = 100, message = "Title must be less than 100 characters") String title,
        @NotBlank(message = "Description cannot be blank") @Size(max = 500, message = "Description must be less than 500 characters") String description) {
}