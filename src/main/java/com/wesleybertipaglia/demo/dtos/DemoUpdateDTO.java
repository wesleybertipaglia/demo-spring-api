package com.wesleybertipaglia.demo.dtos;

import java.util.Optional;

public record DemoUpdateDTO(Optional<String> title, Optional<String> description) {
}