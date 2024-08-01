package com.wesleybertipaglia.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.wesleybertipaglia.demo.dtos.DemoCreateDTO;
import com.wesleybertipaglia.demo.dtos.DemoReadDTO;
import com.wesleybertipaglia.demo.dtos.DemoUpdateDTO;
import com.wesleybertipaglia.demo.services.DemoService;

import java.util.List;

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
    public List<DemoReadDTO> listDemos() {
        return demoService.listDemos();
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
