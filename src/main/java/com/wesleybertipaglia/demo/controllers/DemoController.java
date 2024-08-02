package com.wesleybertipaglia.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import com.wesleybertipaglia.demo.dtos.DemoCreateDTO;
import com.wesleybertipaglia.demo.dtos.DemoReadDTO;
import com.wesleybertipaglia.demo.dtos.DemoUpdateDTO;
import com.wesleybertipaglia.demo.services.DemoService;

@RestController
@RequestMapping("/demos")
public class DemoController {

    @Autowired
    DemoService demoService;

    @PostMapping
    public DemoReadDTO createDemo(@RequestBody DemoCreateDTO demoCreateDTO) {
        return demoService.createDemo(demoCreateDTO);
    }

    @GetMapping
    public Page<DemoReadDTO> listDemos(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "title") String sort,
            @RequestParam(defaultValue = "asc") String direction) {
        return demoService.listDemos(page, size, sort, direction);
    }

    @GetMapping("{id}")
    public DemoReadDTO getDemo(@PathVariable("id") Long id) {
        return demoService.getDemo(id);
    }

    @PutMapping("{id}")
    public DemoReadDTO updateDemo(@PathVariable("id") Long id, @RequestBody DemoUpdateDTO demoUpdateDTO) {
        return demoService.updateDemo(id, demoUpdateDTO);
    }

    @DeleteMapping("{id}")
    public void deleteDemo(@PathVariable("id") Long id) {
        demoService.deleteDemo(id);
    }
}
